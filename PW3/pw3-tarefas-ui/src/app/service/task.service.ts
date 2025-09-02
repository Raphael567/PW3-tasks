import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../model/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl = "http://localhost:8080/tarefas";
  private http = inject(HttpClient);

  findAll(): Observable<Task[]> {
    return this.http.get<Task[]>(this.apiUrl);
  }

  deleteTask(id: number | undefined): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
    //return this.http.delete<boolean>(`${this.apiUrl}/${id}`);
  }

  constructor() { }
}
