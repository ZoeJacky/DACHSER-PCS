import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { IncomeService } from '../_services/income.service';

@Component({
  selector: 'app-payment-admin',
  imports: [RouterOutlet,RouterLink, CommonModule,RouterModule],
  templateUrl: './payment-admin.html',
  styleUrl: './payment-admin.css',
  standalone: true
})
export class PaymentAdmin implements OnInit{
  PATH_OF_API = "http://localhost:8080";

  constructor(
    private incomeService: IncomeService,
  ) {}

  incomes: any[] = [];
  http = inject(HttpClient);
  router = inject(Router);

  ngOnInit(): void {
    this.loadIncomes();
  }

  // Function to fetch the list of incomes
  loadIncomes(): void {
    this.http
      .get(this.PATH_OF_API + '/api/incomes')
      .subscribe((response:any) => {
        console.log('incomes:',response);
        this.incomes = response;
    });
  }

  // Function to handle adding a new shipment
  // addNewShipment(): void {
  //   this.http
  //   .post(this.PATH_OF_API + '/api/shipments',{shipment:this.form.getRawValue()})
  //   .subscribe((response:any) =>{
  //     console.log('response',response);
  //   });
  //   this.router.navigate(['/add-shipment']);
  // }

  // Function to handle updating a income by its ID
  updateIncome(id: number): void {
    // Navigate to the 'edit' income page, passing the income id as a parameter
    this.router.navigate([`/update-income/${id}`]);
  }

  // Function to handle removing a income by its ID
  removeIncome(id: number): void {
    if (confirm('Are you sure you want to delete this income?')) {
      this.http.delete(this.PATH_OF_API + '/api/incomes/' + id).subscribe(
      (response) => {
        console.log('Delete incomes:',response);
        this.loadIncomes();
    });
    }
  }

}
