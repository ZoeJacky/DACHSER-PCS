import { Component, inject, OnInit, signal } from '@angular/core';
import { Router, RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { AuthService } from './_services/auth.service';
import { HttpClient } from '@angular/common/http';
import { UserInterface } from './user/user.interface';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, CommonModule,RouterModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
  standalone:true
})
export class App implements OnInit {
  protected readonly title = signal('pcs-frontend');
  PATH_OF_API = "http://localhost:8080";
  authService = inject(AuthService);
  http = inject(HttpClient);
  router = inject(Router);

  ngOnInit():void{
    this.http
    .get<{user: UserInterface}>(this.PATH_OF_API + '/api/user')
    .subscribe({
      next:(response:any)=>{
        const roleList = response.roles.map((role: any) => role.roleName);
        console.log('response after reloading:', response);
        // this.authService.currentUserSig.set(response);
        this.authService.currentUserSig.set({
          email: response.email,
          userName: response.userName,
          token: 'null',
          role: roleList
        });
        console.log('currentUserSig after reploading', this.authService.currentUserSig);
      },
      error:()=>{
        this.authService.currentUserSig.set(null);
      },
    });
  }

  logout():void{
    console.log('logout');
    localStorage.setItem('token','');
    this.authService.currentUserSig.set(null);
    this.router.navigateByUrl('/home');
  }
}
