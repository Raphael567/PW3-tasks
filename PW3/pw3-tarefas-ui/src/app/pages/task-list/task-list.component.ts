import { Component, OnInit, inject } from '@angular/core';
import { Task } from '../../model/task';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TaskService } from '../../service/task.service';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
})
export class TaskListComponent implements OnInit {

  tasks: Task[] = [];
  private router = inject(Router);
  private TaskService = inject(TaskService);

  ngOnInit(): void {
    this.TaskService.findAll().subscribe((_tasks) => {
      this.tasks = _tasks;
    })
  }

  // ngOnInit(): void {
  //   this.tasks = [{id : 1, "titulo":"Revisar código", "descricao":"Revisar o código desenvolvido na última semana","dataLimite":"2024-11-10","status":"PENDING","responsavel":"Desenvolvedor"},
  //                 {id : 2, "titulo":"Verificar logs de erro", "descricao":"Verificar e analisar logs de erro","dataLimite":"2024-12-10","status":"IN_PROGRESS","responsavel":"Desenvolvedor"}];
  // }

  newTask() {
    this.router.navigate(['/tasks/new']);
  }

  deleteTask(id: number | undefined) {
    this.TaskService.deleteTask(id).subscribe({
      next: () => {
        console.log("Item deletado com sucesso");
      },
      error: (err) => {
        console.log(err);
      }
    })

    if (id && confirm('Tem certeza que deseja excluir esta tarefa?')) {
      console.log("Exclusão realizada");
    }
  }

  editTask(id: number | undefined) {
    if (id) this.router.navigate(['/tasks', id]);
  }
}
