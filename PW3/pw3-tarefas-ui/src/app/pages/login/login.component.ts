import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { Login } from '../../../service/login.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  private formBuilder = inject(FormBuilder);
  private loginService = inject(Login);
  private router = inject(Router);

  loginForm!: FormGroup;
  errorMessage: string = '';

  constructor() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login(){
    if (this.loginForm.valid) {
      const username= this.loginForm.value.username;
      const password= this.loginForm.value.password;
      this.loginService.autenticar(username, password).subscribe({
        next: (value)=> {
          this.router.navigateByUrl('/')
          this.loginForm.reset();
        },
        error:(err)=> {
          console.log('Problema na autenticação',err)
        },
      })
    }
  }

}
