import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CursosComponent } from './components/cursos/cursos.component';
import { TemaListComponent } from './components/tema-list/tema-list.component';
import { TemaFormComponent } from './components/tema-form/tema-form.component';
import { AlumnoListComponent } from './components/alumno-list/alumno-list.component';
import { AlumnoFormComponent } from './components/alumno-form/alumno-form.component';
import { DocenteListComponent } from './components/docente-list/docente-list.component';
import { DocenteFormComponent } from './components/docente-form/docente-form.component';
import { CursoFormComponent } from './components/curso-form/curso-form.component';

export const routes: Routes = [
  { path: '', redirectTo: '/cursos', pathMatch: 'full' },
  { path: 'cursos', component: CursosComponent },
  { path: 'cursos/:id', component: CursoFormComponent },

  { path: 'cursos/nuevo', component: CursoFormComponent },

  { path: 'temas', component: TemaListComponent },
  { path: 'temas/:id', component: TemaFormComponent },
  // alumnos
  { path: 'alumnos', component: AlumnoListComponent },
  { path: 'alumnos/:id', component: AlumnoFormComponent },
  // docentes
  { path: 'docentes', component: DocenteListComponent },
  { path: 'docentes/:id', component: DocenteFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
