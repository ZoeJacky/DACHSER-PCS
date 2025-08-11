import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { ShipmentInterface } from '../shipment/shipment.interface';
import { FormBuilder, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-fin-dep',
  imports: [RouterLink, RouterOutlet,CommonModule,RouterModule],
  templateUrl: './fin-dep.html',
  styleUrl: './fin-dep.css',
  standalone:true
})
export class FinanceDep implements OnInit{
  PATH_OF_API = "http://localhost:8080";

  shipments: any[] = [];  // Array to hold shipment data
  http = inject(HttpClient);
  router = inject(Router);
  fb = inject(FormBuilder);

  form = this.fb.nonNullable.group({
    userName: ['', Validators.required],
    password: ['', Validators.required],
  })

  // constructor(
  //   private shipmentService: ShipmentService,
  //   private router: Router  // Inject Router for navigation
  // ) { }

  ngOnInit(): void {
    this.loadShipments();
  }

  // Function to fetch the list of shipments
  loadShipments(): void {
    this.http
      .get(this.PATH_OF_API + '/api/shipments')
      .subscribe((response:any) => {
        console.log('shipments:',response);
        this.shipments = response;
    });
  }

  // Function to handle adding a new shipment
  addNewShipment(): void {
    this.http
    .post(this.PATH_OF_API + '/api/shipments',{shipment:this.form.getRawValue()})
    .subscribe((response:any) =>{
      console.log('response',response);
    });
    this.router.navigate(['/add-shipment']);
  }

  // Function to handle updating a shipment by its ID
  updateShipment(id: number): void {
    // Navigate to the 'edit' shipment page, passing the shipment id as a parameter
    this.router.navigate([`/update-shipment/${id}`]);
  }

  // Function to handle removing a shipment by its ID
  // removeShipment(id: number): void {
  //   if (confirm('Are you sure you want to delete this shipment?')) {
  //     this.shipmentService.deleteShipment(id).subscribe(() => {
  //       // After successful deletion, reload the shipments list
  //       this.loadShipments();
  //     });
  //   }
  // }

}
