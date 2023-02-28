import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Condition } from '../interfaces/condition';
import { ConditionSymptom } from '../interfaces/conditionSymptoms';

@Injectable({
  providedIn: 'root'
})
export class ConditionService {

  constructor(private http: HttpClient) { }

  getConditions(){
    console.log("test")
    return this.http.get<Condition[]>(environment.host+"Condition/getAll");
  }

  getConditionById(id: string){
    return this.http.get<Condition>(environment.host+"Condition/getById?id="+id);
  }

  getConditionsBySymptoms(symptoms: string[]){
    return this.http.post<ConditionSymptom[]>(environment.host+"Condition/getConditionsBySymptoms", symptoms);
  }
}
