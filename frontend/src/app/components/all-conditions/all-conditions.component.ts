import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Condition } from 'src/app/interfaces/condition';
import { ConditionService } from 'src/app/services/condition.service';

@Component({
  selector: 'app-all-conditions',
  templateUrl: './all-conditions.component.html',
  styleUrls: ['./all-conditions.component.css']
})
export class AllConditionsComponent implements OnInit {

  conditions: Condition[] = []

  constructor(
    private router: Router,
    private conditionService: ConditionService
  ) { }

  ngOnInit(): void {
    console.log("test")
    this.conditionService.getConditions().subscribe({
      next: (res)=>{
        console.log("test")
        console.log(res)
        this.conditions = res
      }

    })
  }

  showDetails(conditionId:string){
    this.router.navigate(['/condition-detail/'+conditionId])
  }

}
