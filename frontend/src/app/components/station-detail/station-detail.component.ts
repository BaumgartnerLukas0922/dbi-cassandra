import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from 'src/app/interfaces/patient';
import { PatientService } from 'src/app/services/patient.service';
import { StationService } from 'src/app/services/station.service';

@Component({
  selector: 'app-station-detail',
  templateUrl: './station-detail.component.html',
  styleUrls: ['./station-detail.component.css']
})
export class StationDetailComponent implements OnInit {
  stationId: string = ""
  patients: Patient[]  = []

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private patientService: PatientService
  ) { }

  ngOnInit(): void {
    if(this.route.snapshot.paramMap.get('stationId') != null){
      this.stationId = this.route.snapshot.paramMap.get('stationId')!
      this.patientService.getByStation(this.stationId).subscribe({
        next: res => {
            this.patients = res
        }
      })

    }
  }

}
