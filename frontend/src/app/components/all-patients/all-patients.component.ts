import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from '../../interfaces/patient';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-all-patients',
  templateUrl: './all-patients.component.html',
  styleUrls: ['./all-patients.component.css']
})
export class AllPatientsComponent implements OnInit {

  patients: Patient[] = []

  isCurrentlyInHospital: string = "All"
  isDiagnosed: string = "All"
  name_search = ""

  constructor(
    private patientService: PatientService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.patientService.getPatients().subscribe({
      next: (res)=>{
        this.patients = res
      }
    })
  }

  showDetails(patientId:string){
    this.router.navigate(['/detail/'+patientId])
  }
}
