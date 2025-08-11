import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IncomeInterface } from '../add-income/income.interface';
import { IncomeService } from '../_services/income.service';

@Component({
  selector: 'app-update-income',
  imports: [ReactiveFormsModule],
  templateUrl: './update-income.html',
  styleUrl: './update-income.css',
  standalone: true
})
export class UpdateIncome {
  PATH_OF_API = "http://localhost:8080";
  fb = inject(FormBuilder);
  http = inject(HttpClient);
  router = inject(Router);

  constructor(
    private route: ActivatedRoute,
    ) {}

  incomeForm = this.fb.nonNullable.group({
  amount: ['', Validators.required],
  source: ['', Validators.required],
  shipmentId: ['', Validators.required],
  })

  onSubmit():void{
    const id = +this.route.snapshot.paramMap.get('id')!;
    console.log('Id of the update income is:', id);
    this.http
        .put<{income:IncomeInterface}>(this.PATH_OF_API + `/api/incomes/${id}`, {
          amount: this.incomeForm.get('amount')?.value,
          source: this.incomeForm.get('source')?.value,
          date: new Date(),
          shipmentId: this.incomeForm.get('shipmentId')?.value,
        })
        .subscribe((response:any) => {
          console.log('update income',response);
          this.router.navigateByUrl('/payment-admin');
        });
        console.log('update income!');
        console.log('form',this.incomeForm.getRawValue());

  }

}
