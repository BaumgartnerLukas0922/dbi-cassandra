import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Diagnosis } from 'src/app/interfaces/diagnosis';
import { Patient } from 'src/app/interfaces/patient';
import { Station } from 'src/app/interfaces/station';
import { DiagnosisService } from 'src/app/services/diagnosis.service';
import { PatientService } from 'src/app/services/patient.service';
import { StationService } from 'src/app/services/station.service';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {
  patientId: string = ""
  diagnosises: Diagnosis[] = []
  numberOfBeds: number = 0
  admitClicked: boolean = false

  stations: Station[] = []

  patient: Patient = {
    id: "",
    firstName: "",
    height: 0,
    currentlyInHospital: false,
    diagnosed: false,
    lastName: "",
    ssn: "",
    weight: 0,
    dob: ""
}

  station: Station = {
    id: "",
    name: "",
    totalNumberOfBeds: 0
  }

  selectedStation: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private patientService: PatientService,
    private stationService: StationService,
    private diagnosisService: DiagnosisService,
  ) { }

  ngOnInit(): void {
    if(this.route.snapshot.paramMap.get('patientId') != null){
      this.stationService.getAllStation().subscribe({
        next: res => {
          this.stations = res
        }
      })

      this.patientId = this.route.snapshot.paramMap.get('patientId')!
      this.loadPatient()
    }
    else{
      this.router.navigate(['/home'])
    }
  }


  loadPatient(){

    this.patientService.getPatientById(this.patientId).subscribe(
      {
        next: res => {
          this.patient = res;
          this.station = this.stations.find(s => s.id == this.patient.id)!
          this.stationService.getStationById(this.patient.stationId!).subscribe(
            {
              next: res => {
                console.log(res.name + res.id)
                this.station = res;

                this.stationService.getAmountOnStation(this.patient.stationId!).subscribe({
                  next: res => {
                    this.numberOfBeds = res;
                  }
                });
              }
            }
          )
          console.log("Curr: "+this.patient.currentlyInHospital + " Diagnose: "+this.patient.diagnosed)

          this.diagnosisService.getDiagnosisForPatient(this.patientId).subscribe(
            {
              next: res => {
                this.diagnosises = res;
                console.log(this.diagnosises)
              }
            }
          )
        }
      }
    )
  }

  showDetails(id:string){
    this.router.navigate(['/diagnosisDetail/'+id])
  }


  admitClclicked() {
    if(this.admitClicked == true && this.selectedStation != null){
      console.log("clicked")
      this.patientService.assignStation(this.selectedStation.id, this.patient.id).subscribe({
        next: res => {
          console.log("admitted")
          this.loadPatient()
          this.admitClicked = false
        }
      })
    }
    else
    {
      this.admitClicked = true
    }

  }

  releaseClclicked(){
    this.patientService.releasePatient(this.patient.id).subscribe(
      {
        next: res => {
          console.log("Curr: "+this.patient.currentlyInHospital + " Diagnose: "+this.patient.diagnosed)
          this.loadPatient()
        }
      }
    )
  }


  diagnoseClclicked(id:string){
    this.router.navigate(['/diagnosePatient/'+id])
  }


}
