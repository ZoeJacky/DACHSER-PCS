import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CostInterface } from './cost.interface';

@Component({
  selector: 'app-add-cost',
  imports: [ReactiveFormsModule],
  templateUrl: './add-cost.html',
  styleUrl: './add-cost.css'
})
export class AddCost {
  PATH_OF_API = "http://localhost:8080";
  fb = inject(FormBuilder);
  http = inject(HttpClient);
  router = inject(Router);

  costForm = this.fb.nonNullable.group({
  amount: ['', Validators.required],
  category: ['', Validators.required],
  shipmentId: ['', Validators.required],
  })

  onSubmit():void{
      this.http
          .post<{cost:CostInterface}>(this.PATH_OF_API + '/api/costs', {
            amount: this.costForm.get('amount')?.value,
            category: this.costForm.get('category')?.value,
            date: new Date(),
            shipmentId: this.costForm.get('shipmentId')?.value,
          })
          .subscribe((response:any) => {
            console.log('add new cost',response);
            this.router.navigateByUrl('/cost-admin');
          });
          console.log('add new cost!');
          console.log('form',this.costForm.getRawValue());

  }

}
