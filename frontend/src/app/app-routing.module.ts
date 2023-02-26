import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddPatientComponent } from './components/add-patient/add-patient.component';
import { AllConditionsComponent } from './components/all-conditions/all-conditions.component';
import { AllPatientsComponent } from './components/all-patients/all-patients.component';
import { DiagnosePatientComponent } from './components/diagnose-patient/diagnose-patient.component';
import { DiagnosisDetailsComponent } from './components/diagnosis-details/diagnosis-details.component';
import { HomeComponent } from './components/home/home.component';
import { PatientDetailsComponent } from './components/patient-details/patient-details.component';
import { ShowStationsComponent } from './components/show-stations/show-stations.component';
import { StationDetailComponent } from './components/station-detail/station-detail.component';

const routes: Routes = [
  { path: 'home', component: AllPatientsComponent },
  { path: 'detail/:patientId', component: PatientDetailsComponent },
  { path: 'diagnosisDetail/:diagnosisId', component: DiagnosisDetailsComponent },
  { path: 'diagnosePatient/:patientId', component: DiagnosePatientComponent },
  { path: 'addPatient', component: AddPatientComponent },
  { path: 'conditions', component: AllConditionsComponent },
  { path: 'showStations', component: ShowStationsComponent },
  { path: 'showStations/:stationId', component: StationDetailComponent },
  { path: '**', component: AllPatientsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
