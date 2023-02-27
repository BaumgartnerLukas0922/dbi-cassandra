import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { AllPatientsComponent } from './components/all-patients/all-patients.component';
import { FormsModule } from '@angular/forms';
import { PatientDetailsComponent } from './components/patient-details/patient-details.component';
import { DiagnosisDetailsComponent } from './components/diagnosis-details/diagnosis-details.component';
import { AddPatientComponent } from './components/add-patient/add-patient.component';
import { DiagnosePatientComponent } from './components/diagnose-patient/diagnose-patient.component';
import { AllConditionsComponent } from './components/all-conditions/all-conditions.component';
import { ShowStationsComponent } from './components/show-stations/show-stations.component';
import { StationDetailComponent } from './components/station-detail/station-detail.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AllPatientsComponent,
    PatientDetailsComponent,
    DiagnosisDetailsComponent,
    AddPatientComponent,
    DiagnosePatientComponent,
    AllConditionsComponent,
    ShowStationsComponent,
    StationDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
