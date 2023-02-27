import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Patient } from '../interfaces/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) { }

  
  getPatients(){
    return this.http.get<Patient[]>(environment.host+"Patient/getAll");
  }

  getPatientById(id: string){
    return this.http.get<Patient>(environment.host+"Patient/getById?id="+id);
  }

  createPatient(patient: Patient){
    return this.http.post(environment.host+"Patient/create", patient);
  }

  checkSsn(ssn: string){
    return this.http.get<boolean>(environment.host+"Patient/checkSsn?ssn="+ssn);
  }

  assignStation(stationId: string, patientId: string){
    return this.http.post(environment.host+"Patient/assignStation?patientId="+patientId+"&stationId="+stationId, {});
  }

  releasePatient(patientId: string){
    return this.http.post(environment.host+"Patient/releasePatient?id="+patientId, {});
  }

  getByStation(stationId: string){
    return this.http.get<Patient[]>(environment.host+"Patient/getByStation?id="+stationId);
  }

}
