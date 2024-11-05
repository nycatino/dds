import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-alumno-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './alumno-list.component.html',
  styleUrl: './alumno-list.component.css'
})
export class AlumnoListComponent {
  alumnos: any[] = [];

  constructor(private apiService: ApiService, private router: Router) {}
  /* Un constructor es un método especial de una clase que se llama automáticamente cuando se crea una instancia (u objeto) de esa clase. Su principal función es inicializar los atributos de la clase y establecer el estado inicial del objeto. El constructor puede recibir parámetros, lo que permite pasar valores al crear una instancia de la clase.
  Las dependencias son los componentes o servicios que una clase necesita, y la inyección de dependencias permite que esas dependencias sean proporcionadas externamente, mejorando la modularidad y la mantenibilidad del código.
  */ ngOnInit(): void {
    this.getAlumnos();
  }

  getAlumnos() {
    this.apiService.getAlumnos().subscribe(data => {
      this.alumnos = data;
    });
  }

  editarAlumno(alumnoId: number): void {
    this.router.navigate(['/alumnos', alumnoId]);
  }

  eliminarAlumno(alumnoId: number): void {
    if (confirm('¿Estás seguro de que deseas eliminar este alumno?')) {
      this.apiService.eliminarAlumno(alumnoId).subscribe(() => {
        alert('Alumno eliminado correctamente');
        this.getAlumnos(); // Actualiza la lista de cursos después de eliminar uno
      });
    }
  }

  nuevoAlumno(): void {
    this.router.navigate(['/alumnos', 'nuevo']);
  }

  volver() : void {
    this.router.navigate(['/cursos']);
  }
}
