import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cursos',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.css']
})
export class CursosComponent implements OnInit {
/*   export class: Define la clase CursosComponent, que puede ser utilizada en otras partes de la aplicación.
  implements OnInit: Indica que esta clase implementa la interfaz OnInit, lo que requiere que se defina el método ngOnInit(). */
  cursos: any[] = [];
  fecha: string = '';
  docentes: any[] = [];
  docenteId!: number;

  constructor(private apiService: ApiService, private router: Router) {}
  /* Un constructor es un método especial de una clase que se llama automáticamente cuando se crea una instancia (u objeto) de esa clase. Su principal función es inicializar los atributos de la clase y establecer el estado inicial del objeto. El constructor puede recibir parámetros, lo que permite pasar valores al crear una instancia de la clase.
  Las dependencias son los componentes o servicios que una clase necesita, y la inyección de dependencias permite que esas dependencias sean proporcionadas externamente, mejorando la modularidad y la mantenibilidad del código.
  */ ngOnInit(): void {
    this.getCursos();
    this.getDocentes();
  }
/*   ngOnInit: Este método se llama automáticamente después de que Angular ha inicializado todas las propiedades vinculadas a las directivas del componente. Es un buen lugar para realizar inicializaciones.
 */
  getCursos(): void {
    this.apiService.getCursos().subscribe((data) => {
      this.cursos = data;
    });
  }

  /* this.apiService.getCursos(): Llama al método getCursos del servicio ApiService, que devuelve un Observable.
subscribe((data) => { ... }): Se suscribe al observable y, cuando recibe datos, los asigna a la propiedad cursos.
 */
  buscarPorFecha(): void {
    if (this.fecha) {
      this.apiService.getCursosPorFecha(this.fecha).subscribe((data) => {
        this.cursos = data;
      });
    }
  }


  buscarPorDocente() {
    this.apiService.getCursosVigentesDeDocente(this.docenteId).subscribe((data) => {
      this.cursos = data;
  });
  }

  editarCurso(cursoId: number): void {
    this.router.navigate(['/cursos', cursoId]);
  }

  editarDocentes(): void {
    this.router.navigate(['/docentes']);
  }

  editarTemas(): void {
    this.router.navigate(['/temas']);
  }

  editarAlumnos(): void {
    this.router.navigate(['/alumnos']);
  }

  eliminarCurso(cursoId: number): void {
    if (confirm('¿Estás seguro de que deseas eliminar este curso?')) {
      this.apiService.eliminarCurso(cursoId).subscribe(() => {
        alert('Curso eliminado correctamente');
        this.getCursos(); // Actualiza la lista de cursos después de eliminar uno
      });
    }
  }

  nuevoCurso(): void {
    this.router.navigate(['/cursos', 'nuevo']);
  }
  
  getDocentes() {
    this.apiService.getDocentes().subscribe(data => {
      this.docentes = data;
    });
  }
  
}
