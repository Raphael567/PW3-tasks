import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Task } from '../../model/task';
import { TaskService } from '../../service/task.service';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css'],
})
export class TaskFormComponent {

  private fb = inject(FormBuilder);
  private TaskService = inject(TaskService);

  id: number | null = null;

  taskForm = this.fb.group({
    id: [null as number | null],
    titulo: ['', Validators.required],
    descricao: [''],
    responsavel: ['', Validators.required],
    dataLimite: ['', Validators.required],
    status: ['PENDING', Validators.required],
  });

  onSubmit(): void {
    if (this.taskForm.invalid) return;

    if (this.id) {
      console.log("Executa a atualização")
    } else {
      console.log("Executa a inserção")
    }

    const task: Task = this.taskForm.value as Task;

    if (this.id) {
      // Edição
      this.TaskService.editTask(this.id, task).subscribe({
        next: (updatedTask) => {
          console.log('Tarefa atualizada com sucesso', updatedTask);
          this.taskForm.reset();
        },
        error: (err) => {
          console.error('Erro ao atualizar a tarefa', err);
        }
      });
    } else {
      // Criação
      this.TaskService.createTask(task).subscribe({
        next: (createdTask) => {
          console.log('Tarefa criada com sucesso', createdTask);
          this.taskForm.reset();
        },
        error: (err) => {
          console.error('Erro ao criar a tarefa', err);
        }
      });
    }
  }
}
