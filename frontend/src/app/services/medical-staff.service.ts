import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { MedicalStaff } from '../interfaces/medical-staff';

@Injectable({
  providedIn: 'root'
})
export class MedicalStaffService {

  constructor(private http: HttpClient) { }

  getMedicalStaffs(){
    return this.http.get<MedicalStaff[]>(environment.host+"MedicalStaff/getAll");
  }

  getMedicalStaffById(id: string){
    return this.http.get<MedicalStaff>(environment.host+"MedicalStaff/getById?id="+id);
  }
}
