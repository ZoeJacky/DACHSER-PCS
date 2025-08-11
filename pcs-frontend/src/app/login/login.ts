import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../_services/auth.service';
import { UserInterface } from '../user/user.interface';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
  standalone: true
})
export class Login{
  PATH_OF_API = "http://localhost:8080";

  fb = inject(FormBuilder);
  http = inject(HttpClient);
  authService = inject(AuthService);
  router = inject(Router);

  form = this.fb.nonNullable.group({
    userName: ['', Validators.required],
    password: ['', Validators.required],
  })

  onSubmit():void{
    this.http
    .post<{user:UserInterface}>(this.PATH_OF_API + '/authenticate', {
      userName: this.form.get('userName')?.value,
      password: this.form.get('password')?.value
    })
    .subscribe((response:any) => {
      console.log('response after login',response);
      const roleList = response.userDto.role.map((role: any) => role.roleName);
      localStorage.setItem('token',response.jwtToken);
      // this.authService.currentUserSig.set(response.user);
      this.authService.currentUserSig.set({
        email: response.userDto.email,
        userName: response.userDto.userName,
        token: response.jwtToken,
        role: roleList
      });
      this.router.navigateByUrl('/');
    });
    console.log('login');
    console.log('form',this.form.getRawValue());
  }

}
