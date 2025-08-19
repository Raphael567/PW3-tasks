import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Task } from '../../model/task';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css'],
})
export class TaskFormComponent {

  private fb = inject(FormBuilder);

  id: string | null = null;

  taskForm = this.fb.group({
    id: [null as number | null],
    title: ['', Validators.required],
    description: [''],
    responsible: ['', Validators.required],
    dueDate: ['', Validators.required],
    status: ['PENDING', Validators.required],
  });

  onSubmit(): void {
    if (this.taskForm.invalid) return;

    const task: Task = this.taskForm.value as Task;

    if (this.id) {
      console.log("Executa a atualização")
    } else {
      console.log("Executa a inserção")
    }
  }
}
