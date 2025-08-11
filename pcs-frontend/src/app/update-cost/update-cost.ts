import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CostInterface } from '../add-cost/cost.interface';

@Component({
  selector: 'app-update-cost',
  imports: [ReactiveFormsModule],
  templateUrl: './update-cost.html',
  styleUrl: './update-cost.css'
})
export class UpdateCost {
  PATH_OF_API = "http://localhost:8080";
  fb = inject(FormBuilder);
  http = inject(HttpClient);
  router = inject(Router);

  constructor(
    private route: ActivatedRoute,
    ) {}

  costForm = this.fb.nonNullable.group({
  amount: ['', Validators.required],
  category: ['', Validators.required],
  shipmentId: ['', Validators.required],
  })

  onSubmit():void{
    const id = +this.route.snapshot.paramMap.get('id')!;
    console.log('Id of the update cost is:', id);
    this.http
        .put<{cost:CostInterface}>(this.PATH_OF_API + `/api/costs/${id}`, {
          amount: this.costForm.get('amount')?.value,
          category: this.costForm.get('category')?.value,
          date: new Date(),
          shipmentId: this.costForm.get('shipmentId')?.value,
        })
        .subscribe((response:any) => {
          console.log('update cost',response);
          this.router.navigateByUrl('/cost-admin');
        });
        console.log('update cost!');
        console.log('form',this.costForm.getRawValue());

  }


}
