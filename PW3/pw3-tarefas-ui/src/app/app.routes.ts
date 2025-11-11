import { Routes } from '@angular/router';
import { TaskListComponent } from './pages/task-list/task-list.component';
import { TaskFormComponent } from './pages/task-form/task-form.component';
import { LoginComponent } from './pages/login/login.component';

export const routes: Routes = [
  { path: '', redirectTo: 'tarefas', pathMatch: 'full' },

  { path: 'tarefas', component: TaskListComponent },
  { path: 'tarefas/novo', component: TaskFormComponent },
  { path: 'tarefas/:id', component: TaskFormComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: 'tarefas' }
];
