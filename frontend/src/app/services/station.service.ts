import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Station } from '../interfaces/station';

@Injectable({
  providedIn: 'root'
})
export class StationService {

  constructor(private http: HttpClient) { }

  
  getStationById(id: String){
    return this.http.get<Station>(environment.host+"Station/getById?id="+id);
  }

  getAllStation(){
    return this.http.get<Station[]>(environment.host+"Station/getAll");
  }

  getAmountOnStation(stationId: string){
    return this.http.get<number>(environment.host+"Station/getAmountOnStation?id="+stationId);
  }
}
