import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Condition } from 'src/app/interfaces/condition';
import { Diagnosis } from 'src/app/interfaces/diagnosis';
import { MedicalStaff } from 'src/app/interfaces/medical-staff';
import { Patient } from 'src/app/interfaces/patient';
import { ConditionService } from 'src/app/services/condition.service';
import { DiagnosisService } from 'src/app/services/diagnosis.service';
import { MedicalStaffService } from 'src/app/services/medical-staff.service';
import { PatientService } from 'src/app/services/patient.service';
import { StationService } from 'src/app/services/station.service';

@Component({
  selector: 'app-diagnosis-details',
  templateUrl: './diagnosis-details.component.html',
  styleUrls: ['./diagnosis-details.component.css']
})
export class DiagnosisDetailsComponent implements OnInit {

  diagnosisId: string = ""
  Diagnosis: Diagnosis = {
    id: '',
    medicalStaffId: '',
    daysInHospital: 0,
    conditionId: '',
    patientId: '',
    diagnosedOn: new Date()
  }
  MedicalStaff: MedicalStaff = {
    id:'',
    firstName: '',
    lastName: '',
    dob: '',
    salary: 0,
    hireDate: 0,
    staffDesignation: ''
  }
  Condition: Condition = {
    id: '',
    name: '',
    description: '',
    symptoms: []
  }
  Patient: Patient = {
    id: '',
    firstName: '',
    lastName: '',
    height: 0,
    weight: 0,
    ssn: '',
    isDiagnosed: false,
    isCurrentlyInHospital: false,
    dob: ''
  }

  constructor(
    private route: ActivatedRoute,
    private diagnosisService: DiagnosisService,
    private medicalStaffService: MedicalStaffService,
    private conditionService: ConditionService,
    private patientService: PatientService) { }

  ngOnInit(): void {

    if(this.route.snapshot.paramMap.get('diagnosisId') != null){
      this.diagnosisId = this.route.snapshot.paramMap.get('diagnosisId')!
      this.diagnosisService.getDiagnosisById(this.diagnosisId).subscribe(
        {
          next: res => {
            this.Diagnosis = res;

            this.medicalStaffService.getMedicalStaffById(this.Diagnosis.medicalStaffId).subscribe(
              {
                next: res => {
                  this.MedicalStaff = res;
                }
              }
            )
            this.conditionService.getConditionById(this.Diagnosis.conditionId).subscribe(
              {
                next: res =>{
                  this.Condition = res;
                }
              }
            )
            this.patientService.getPatientById(this.Diagnosis.patientId).subscribe(
              {
                next: res =>{
                  this.Patient = res;
                }
              }
            )
          }
        }
      )

  }
  
}

}
