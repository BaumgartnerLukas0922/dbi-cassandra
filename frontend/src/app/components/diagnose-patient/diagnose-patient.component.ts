import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/interfaces/patient';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientService } from 'src/app/services/patient.service';
import { ConditionService } from 'src/app/services/condition.service';
import { ConditionSymptom } from 'src/app/interfaces/conditionSymptoms';
import { MedicalStaff } from 'src/app/interfaces/medical-staff';
import { MedicalStaffService } from 'src/app/services/medical-staff.service';
import { Diagnosis } from 'src/app/interfaces/diagnosis';
import { DiagnosisService } from 'src/app/services/diagnosis.service';


@Component({
  selector: 'app-diagnose-patient',
  templateUrl: './diagnose-patient.component.html',
  styleUrls: ['./diagnose-patient.component.css']
})
export class DiagnosePatientComponent implements OnInit {

  diagnosis: Diagnosis = {
    id: '',
    medicalStaffId: '',
    daysInHospital: 0,
    conditionId: '',
    patientId: '',
    diagnosedOn: new Date()
  }

  toDateTime!: number;

  symptoms: string[] = [];
  medicalStaff: MedicalStaff[] = [];
  selectedStaff: MedicalStaff = {
    id : '',
    firstName: '',
    lastName: '',
    dob: '',
    salary: 0,
    hireDate: 0,
    staffDesignation: ''
  }

  patientId: string = ""
  symptom: string = ""
  idx = 0;

  conditionsBySymptoms: ConditionSymptom[] = []
  showTable: boolean = false;


  patient: Patient = {
    id: "", 
    firstName: "", 
    height: 0,  
    isCurrentlyInHospital: false, 
    isDiagnosed: false, 
    lastName: "", 
    ssn: "", 
    weight: 0,
    dob: ""
}

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private patientService: PatientService,
    private conditionService: ConditionService,
    private medicalStaffService: MedicalStaffService,
    private diagnosisService: DiagnosisService
  ) { }

  ngOnInit(): void {
    if(this.route.snapshot.paramMap.get('patientId') != null){
      this.patientId = this.route.snapshot.paramMap.get('patientId')!

      this.patientService.getPatientById(this.patientId).subscribe(
        {
          next: res => {
            this.patient = res;
          }
        }
      )

      this.medicalStaffService.getMedicalStaffs().subscribe(
        {
          next: res => {
            res.forEach(element => {
              if(element.staffDesignation == '0'){
                this.medicalStaff.push(element);
              }
            });
          }
        }
      )
    }
    else{
      this.router.navigate(['/home'])
    }
    
  }

  selectedEntry: any;

    onSelectionChange(condition: any) {
        this.selectedEntry = condition;
        console.log(this.selectedEntry)
    }

  addSymptom(){
    if(this.symptom != ""){
    this.symptoms.push(this.symptom);
    this.symptom = "";
    }

  }

  deleteSymptom(actSymptom: string)
  {
    const index: number = this.symptoms.indexOf(actSymptom);
    this.symptoms.splice(index,1)

  }

  getDiagnosis(){
    this.conditionService.getConditionsBySymptoms(this.symptoms).subscribe({
      next: res =>{
        this.conditionsBySymptoms = res;
      }})
      this.showTable = true;

  }

  diagnosePatient(){
    this.diagnosis.conditionId = this.selectedEntry.id
    this.diagnosis.medicalStaffId = this.selectedStaff.id
    this.diagnosis.patientId = this.patient.id
    this.diagnosis.daysInHospital = this.toDateTime

    console.log(this.diagnosis)

    this.diagnosisService.createDiagnosis(this.diagnosis).subscribe({
      next: res => {
        this.router.navigate(['detail/' + this.patient.id])
      }
    })

    

  }

}
