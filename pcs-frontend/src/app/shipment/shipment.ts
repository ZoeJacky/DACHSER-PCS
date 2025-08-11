import { HttpClient } from '@angular/common/http';
import { Component,inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { ShipmentInterface } from './shipment.interface';

@Component({
  selector: 'app-shipment',
  imports: [ReactiveFormsModule],
  templateUrl: './shipment.html',
  styleUrl: './shipment.css',
  standalone: true
})
export class Shipment {
    PATH_OF_API = "http://localhost:8080";
    fb = inject(FormBuilder);
    http = inject(HttpClient);
    router = inject(Router);

    shipmentForm = this.fb.nonNullable.group({
    reference: ['', Validators.required],
    description: ['', Validators.required],
    })

    onSubmit():void{
        this.http
            .post<{shipment:ShipmentInterface}>(this.PATH_OF_API + '/api/shipments', {
              // user:this.form.getRawValue(),
              reference: this.shipmentForm.get('reference')?.value,
              description: this.shipmentForm.get('description')?.value
            })
            .subscribe((response:any) => {
              console.log('response',response);
              this.router.navigateByUrl('/');
            });
            console.log('login');
            console.log('form',this.shipmentForm.getRawValue());

    }

}