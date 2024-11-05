import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Curso } from '../models/curso.model';
import { Docente } from '../models/docente.model';
import { Alumno } from '../models/alumno.model';
import { Tema } from '../models/tema.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://localhost:8080'; // Cambia la URL según tu servidor de Spring Boot

  constructor(private http: HttpClient) {}

  // Método para obtener cursos que finalizan en una fecha específica
  getCursosPorFecha(fecha: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/cursos/finalizan`, {
      params: { fecha }
    });
  }

  // Método para obtener el curso vigente de un docente y sus alumnos
  getCursosVigentesDeDocente(docenteId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/docentes/${docenteId}/cursos-vigentes`);
  }
  

  // Métodos estándar para las entidades Curso, Docente, Alumno y Tema
  getCursos(): Observable<any> {
    return this.http.get(`${this.apiUrl}/cursos`);
  }

  eliminarCurso(cursoId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/cursos/${cursoId}`);
  }

  // Método para editar un curso (si se necesita)
  editarCurso(cursoId: number, cursoData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/cursos/${cursoId}`, cursoData);
  }

  getCursoById(id: number): Observable<Curso> {
    return this.http.get<Curso>(`${this.apiUrl}/cursos/${id}`);
  }

  createCurso(curso: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/cursos`, curso);
  }

  updateCurso(id: number, curso: Curso): Observable<Curso> {
    return this.http.put<Curso>(`${this.apiUrl}/cursos/${id}`, curso);
  }

  getTemas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/temas`)
  }
  
  getDocentes(): Observable<Docente[]> {
    return this.http.get<Docente[]>(`${this.apiUrl}/docentes`);
  }
  
  getAlumnos(): Observable<Alumno[]> {
    return this.http.get<Alumno[]>(`${this.apiUrl}/alumnos`);
  }




  getDocenteById(id: number) {
    return this.http.get<Docente>(`${this.apiUrl}/docentes/${id}`);
  }
  
  createDocente(docente: Docente): Observable<any> {
    return this.http.post(`${this.apiUrl}/docentes`, docente);
  }

  updateDocente(id: number, docente: Docente): Observable<Docente> {
    return this.http.put<Docente>(`${this.apiUrl}/docentes/${id}`, docente);
  }

  eliminarDocente(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/docentes/${id}`);
  }



  getTemaById(id: number) {
    return this.http.get<Tema>(`${this.apiUrl}/temas/${id}`);
  }
  
  createTema(tema: Tema): Observable<any> {
    return this.http.post(`${this.apiUrl}/temas`, tema);
  }

  updateTema(id: number, tema: Tema): Observable<Tema> {
    return this.http.put<Tema>(`${this.apiUrl}/temas/${id}`, tema);
  }

  eliminarTema(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/temas/${id}`);
  }



  getAlumnoById(id: number) {
    return this.http.get<Alumno>(`${this.apiUrl}/alumnos/${id}`);
  }
  
  createAlumno(alumno: Alumno): Observable<any> {
    return this.http.post(`${this.apiUrl}/alumnos`, alumno);
  }

  updateAlumno(id: number, alumno: Alumno): Observable<Alumno> {
    return this.http.put<Alumno>(`${this.apiUrl}/alumnos/${id}`, alumno);
  }
  
  eliminarAlumno(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/alumnos/${id}`);
  }
  

}
