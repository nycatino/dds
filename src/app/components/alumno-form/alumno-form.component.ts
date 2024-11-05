import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';
import { Docente } from '../../models/docente.model';
import { Alumno } from '../../models/alumno.model';


@Component({
  selector: 'app-alumno-form',
  standalone: true,
  imports: [FormsModule, RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './alumno-form.component.html',
  styleUrl: './alumno-form.component.css'
})
export class AlumnoFormComponent {
  editarAlumnoForm: FormGroup;
  alumnoId: number = 0;
  alumno!: Alumno;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private apiService: ApiService
  ) {
    this.editarAlumnoForm = this.fb.group({
      nombre: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
    });
  }

  ngOnInit(): void {

    const alumnoIdParam = this.route.snapshot.params['id'];
    if (alumnoIdParam === 'nuevo') {
      this.alumnoId = 0; // Indica un nuevo curso
    } else {
      this.alumnoId = Number(alumnoIdParam);
      this.cargarDatosAlumno(); // Solo carga datos si es un curso existente
    }
 
}

cargarDatosAlumno() {
  this.apiService.getAlumnoById(this.alumnoId).subscribe(alumno => {
    this.alumno = alumno;
    this.editarAlumnoForm.patchValue(alumno);
  });
}

guardar() {
  if (this.editarAlumnoForm.valid) {
    const alumnoData = { ...this.editarAlumnoForm.value };

    if (this.alumnoId === 0) {
      // Crear un curso nuevo
      this.apiService.createAlumno(alumnoData).subscribe(() => {
        this.router.navigate(['/alumnos']);
      });
    } else {
      // Actualizar un curso existente
      this.apiService.updateAlumno(this.alumnoId, alumnoData).subscribe(() => {
        this.router.navigate(['/alumnos']);
      });
    }
  }
}

volver() : void {
  this.router.navigate(['/alumnos']);
}
}
