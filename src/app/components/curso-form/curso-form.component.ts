import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editar-curso',
  standalone: true,
  imports: [FormsModule, RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './curso-form.component.html',
  styleUrl: './curso-form.component.css'
})

export class CursoFormComponent implements OnInit {
  editarCursoForm: FormGroup;
  temas: any[] = []
  docentes: any[] = [];
  alumnos: any[] = [];
  cursoId: number = 0 ; // ID del curso a editar
  alumnosSeleccionados: number[] = [];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private apiService: ApiService
  ) {
    this.editarCursoForm = this.fb.group({
      temaId: ['', Validators.required],
      docenteId: ['', Validators.required],
      alumnosIds: [[]], // Para seleccionar múltiples alumnos
      precio: ['', [Validators.required, Validators.min(0)]],
      fechaInicio: ['', Validators.required],
      fechaFin: ['', Validators.required]
    });
  }

  ngOnInit(): void {
      const cursoIdParam = this.route.snapshot.params['id'];
      if (cursoIdParam === 'nuevo') {
        this.cursoId = 0; // Indica un nuevo curso
      } else {
        this.cursoId = Number(cursoIdParam);
        this.cargarDatosCurso(); // Solo carga datos si es un curso existente
      }
    this.obtenerTemas();
    this.obtenerDocentes();
    this.obtenerAlumnos();
  }

  obtenerTemas() {
    this.apiService.getTemas().subscribe(data => {
      this.temas = data;
    });
  }

  obtenerDocentes() {
    this.apiService.getDocentes().subscribe(data => {
      this.docentes = data;
    });
  }

  obtenerAlumnos() {
    this.apiService.getAlumnos().subscribe(data => {
      this.alumnos = data;
    });
  }

  cargarDatosCurso() {
    this.apiService.getCursoById(this.cursoId).subscribe(curso => {
      this.editarCursoForm.patchValue(curso);
    });
  }

  guardar() {
    if (this.editarCursoForm.valid) {
      const cursoData = { ...this.editarCursoForm.value };
  
      // Convertir docenteId, temaId y alumnosIds a los formatos esperados por la API
      cursoData.docente = { id: cursoData.docenteId };
      delete cursoData.docenteId;
  
      cursoData.tema = { id: cursoData.temaId };
      delete cursoData.temaId;
  
      cursoData.alumnos = cursoData.alumnosIds.map((id: number) => ({ id }));
      delete cursoData.alumnosIds;
  
      if (this.cursoId === 0) {
        // Crear un curso nuevo
        this.apiService.createCurso(cursoData).subscribe(() => {
          this.router.navigate(['/cursos']);
        });
      } else {
        // Actualizar un curso existente
        this.apiService.updateCurso(this.cursoId, cursoData).subscribe(() => {
          this.router.navigate(['/cursos']);
        });
      }
    }
  }

  onAlumnoSelect(alumnoId: number, event: Event): void {
    const isChecked = (event.target as HTMLInputElement).checked;
    if (isChecked) {
      this.alumnosSeleccionados.push(alumnoId);
    } else {
      const index = this.alumnosSeleccionados.indexOf(alumnoId);
      if (index > -1) {
        this.alumnosSeleccionados.splice(index, 1);
      }
    }
    // Actualizar el valor en el formulario
    this.editarCursoForm.get('alumnosIds')?.setValue(this.alumnosSeleccionados);
  }
  
  volver() : void {
    this.router.navigate(['/cursos']);
  }

}