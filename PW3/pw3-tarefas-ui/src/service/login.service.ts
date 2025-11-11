//Raphael Pereira Canuto
//Hellen Novi Salvador

import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface LoginResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class Login {

  private apiUrl = 'http://localhost:8080';
  private http = inject(HttpClient);

  autenticar(username: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, { username,
    password});
  }
}
