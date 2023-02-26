import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Diagnosis } from '../interfaces/diagnosis';

@Injectable({
  providedIn: 'root'
})
export class DiagnosisService {

  constructor(private http: HttpClient) { }

  getDiagnosiss(){
    return this.http.get<Diagnosis[]>(environment.host+"Diagnosis/getAll");
  }

  getDiagnosisById(id: string){
    return this.http.get<Diagnosis>(environment.host+"Diagnosis/getById?id="+id);
  }

  getDiagnosisForPatient(id: string){
    return this.http.get<Diagnosis[]>(environment.host+"Diagnosis/getForPatient?id="+id);
  }

  createDiagnosis(diagnosis: Diagnosis){
    return this.http.post(environment.host+"Diagnosis/create", diagnosis);
  }
}
