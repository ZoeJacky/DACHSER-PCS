import { Component, inject } from '@angular/core';
import { IncomeInterface } from './income.interface';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-income',
  imports: [ReactiveFormsModule],
  templateUrl: './add-income.html',
  styleUrl: './add-income.css',
  standalone: true
})
export class AddIncome {
  PATH_OF_API = "http://localhost:8080";
  fb = inject(FormBuilder);
  http = inject(HttpClient);
  router = inject(Router);

  incomeForm = this.fb.nonNullable.group({
  amount: ['', Validators.required],
  source: ['', Validators.required],
  shipmentId: ['', Validators.required],
  })

  onSubmit():void{
      this.http
          .post<{income:IncomeInterface}>(this.PATH_OF_API + '/api/incomes', {
            amount: this.incomeForm.get('amount')?.value,
            source: this.incomeForm.get('source')?.value,
            date: new Date(),
            shipmentId: this.incomeForm.get('shipmentId')?.value,
          })
          .subscribe((response:any) => {
            console.log('add new income',response);
            this.router.navigateByUrl('/payment-admin');
          });
          console.log('add new income!');
          console.log('form',this.incomeForm.getRawValue());

  }

}
