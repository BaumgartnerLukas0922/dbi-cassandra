import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Station } from 'src/app/interfaces/station';
import { StationService } from 'src/app/services/station.service';

@Component({
  selector: 'app-show-stations',
  templateUrl: './show-stations.component.html',
  styleUrls: ['./show-stations.component.css']
})
export class ShowStationsComponent implements OnInit {

  stations: Station[] = []

  constructor(
    private stationService: StationService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.stationService.getAllStation().subscribe(
      {
        next: res => {
          this.stations = res
        }
      }
    )
  }

  stationClicked(stationId: string){
    this.router.navigate(["showStations/"+stationId])
  }

}
