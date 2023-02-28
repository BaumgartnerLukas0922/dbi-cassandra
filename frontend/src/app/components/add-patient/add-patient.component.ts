import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from 'src/app/interfaces/patient';
import { Station } from 'src/app/interfaces/station';
import { PatientService } from 'src/app/services/patient.service';
import { StationService } from 'src/app/services/station.service';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css']
})
export class AddPatientComponent implements OnInit {
  stations: Station [] = []
  errorMessage: string = ""
  message: string = ""
  hasTriedSubmit: boolean = false
  currDate: Date = new Date()
  selectedDate: Date = new Date();
  errorMessageCreate: string = ""

  patient: Patient = {
    dob: "",
    firstName: "",
    height: 0,
    id: "",
    currentlyInHospital: false,
    diagnosed: false,
    lastName: "",
    ssn: "",
    weight: 0
  }

  selectedStation: Station = {
    id: "",
    name: "",
    totalNumberOfBeds: 0
  }

  constructor(
    private stationService: StationService,
    private patientService: PatientService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.stationService.getAllStation().subscribe({
      next: res => {

        res.forEach(element => {
          let isIn = true

          this.stations.forEach(element2 => {
            if(element.name == element2.name ){
                isIn = false
            }
          });

          if(isIn == true){
            this.stations.push(element)
          }

        });
        console.log(this.stations)
      }
    })
  }

  createPatient(){
    this.selectedDate = new Date(this.patient.dob)
    this.patientService.createPatient(this.patient).subscribe({
      next: res => {
        this.router.navigate(['/home'])
      },
      error: err => {
        console.log(err)
        this.errorMessageCreate = err.error;
        
      }
    })
    this.hasTriedSubmit = true;
  }

  checkSsn(){
    this.patientService.checkSsn(this.patient.ssn).subscribe(
      {
        next: res => {
          if(!res){
            this.errorMessage = "Wrong number"
            this.message = ""
          }else{
            this.errorMessage = ""
            this.message = "Correct"
          }
        },
        error: err =>{
          this.message = ""
          this.errorMessage = err.error
        }
      }
    )
  }

}
