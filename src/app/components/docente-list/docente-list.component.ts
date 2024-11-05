import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-docente-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './docente-list.component.html',
  styleUrl: './docente-list.component.css'
})
export class DocenteListComponent {

  docentes: any[] = [];

  constructor(private apiService: ApiService, private router: Router) {}
  /* Un constructor es un método especial de una clase que se llama automáticamente cuando se crea una instancia (u objeto) de esa clase. Su principal función es inicializar los atributos de la clase y establecer el estado inicial del objeto. El constructor puede recibir parámetros, lo que permite pasar valores al crear una instancia de la clase.
  Las dependencias son los componentes o servicios que una clase necesita, y la inyección de dependencias permite que esas dependencias sean proporcionadas externamente, mejorando la modularidad y la mantenibilidad del código.
  */ ngOnInit(): void {
    this.getDocentes();
  }

  getDocentes() {
    this.apiService.getDocentes().subscribe(data => {
      this.docentes = data;
    });
  }

  editarDocente(docenteId: number): void {
    this.router.navigate(['/docentes', docenteId]);
  }

  eliminarDocente(docenteId: number): void {
    if (confirm('¿Estás seguro de que deseas eliminar este docente?')) {
      this.apiService.eliminarCurso(docenteId).subscribe(() => {
        alert('Docente eliminado correctamente');
        this.getDocentes(); // Actualiza la lista de cursos después de eliminar uno
      });
    }
  }

  nuevoDocente(): void {
    this.router.navigate(['/docentes', 'nuevo']);
  }
  
  volver() : void {
    this.router.navigate(['/cursos']);
  }

}
