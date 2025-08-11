import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterLink, RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-cost-admin',
  imports: [RouterOutlet,RouterLink, CommonModule,RouterModule],
  templateUrl: './cost-admin.html',
  styleUrl: './cost-admin.css',
  standalone: true
})
export class CostAdmin implements OnInit{
  PATH_OF_API = "http://localhost:8080";

  // constructor(
  //   private incomeService: IncomeService,
  // ) {}

  costs: any[] = [];
  http = inject(HttpClient);
  router = inject(Router);

  ngOnInit(): void {
    this.loadCosts();
  }

  // Function to fetch the list of costs
  loadCosts(): void {
    this.http
      .get(this.PATH_OF_API + '/api/costs')
      .subscribe((response:any) => {
        console.log('costs:',response);
        this.costs = response;
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
  updateCost(id: number): void {
    // Navigate to the 'edit' income page, passing the income id as a parameter
    this.router.navigate([`/update-cost/${id}`]);
  }

  // Function to handle removing a income by its ID
  removeCost(id: number): void {
    if (confirm('Are you sure you want to delete this cost?')) {
      this.http.delete(this.PATH_OF_API + '/api/costs/' + id).subscribe(
      (response) => {
        console.log('Delete costs:',response);
        this.loadCosts();
    });
    }
  }

}
