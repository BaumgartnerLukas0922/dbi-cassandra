import { Component, OnInit } from '@angular/core';
import { Patient } from '../../interfaces/patient';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  constructor(
  ) { }

  ngOnInit(): void {
    
  }

}
