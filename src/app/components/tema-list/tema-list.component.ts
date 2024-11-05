import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-tema-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './tema-list.component.html',
  styleUrl: './tema-list.component.css'
})
export class TemaListComponent {

  
  temas: any[] = [];

  constructor(private apiService: ApiService, private router: Router) {}
  /* Un constructor es un método especial de una clase que se llama automáticamente cuando se crea una instancia (u objeto) de esa clase. Su principal función es inicializar los atributos de la clase y establecer el estado inicial del objeto. El constructor puede recibir parámetros, lo que permite pasar valores al crear una instancia de la clase.
  Las dependencias son los componentes o servicios que una clase necesita, y la inyección de dependencias permite que esas dependencias sean proporcionadas externamente, mejorando la modularidad y la mantenibilidad del código.
  */ ngOnInit(): void {
    this.getTemas();
  }

  getTemas() {
    this.apiService.getTemas().subscribe(data => {
      this.temas = data;
    });
  }

  editarTema(temaId: number): void {
    this.router.navigate(['/temas', temaId]);
  }

  eliminarTema(temaId: number): void {
    if (confirm('¿Estás seguro de que deseas eliminar este tema?')) {
      this.apiService.eliminarTema(temaId).subscribe(() => {
        alert('Tema eliminado correctamente');
        this.getTemas(); // Actualiza la lista de cursos después de eliminar uno
      });
    }
  }

  nuevoTema(): void {
    this.router.navigate(['/temas', 'nuevo']);
  }

  volver() : void {
    this.router.navigate(['/cursos']);
  }
}
