import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';
import { Docente } from '../../models/docente.model';

@Component({
  selector: 'app-docente-form',
  standalone: true,
  imports: [FormsModule, RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './docente-form.component.html',
  styleUrl: './docente-form.component.css'
})
export class DocenteFormComponent {
  editarDocenteForm: FormGroup;
  docenteId: number = 0;
  docente!: Docente;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private apiService: ApiService
  ) {
    this.editarDocenteForm = this.fb.group({
      nombre: ['', Validators.required],
      legajo: ['', Validators.required],
    });
  }

  ngOnInit(): void {

    const docenteIdParam = this.route.snapshot.params['id'];
    if (docenteIdParam === 'nuevo') {
      this.docenteId = 0; // Indica un nuevo curso
    } else {
      this.docenteId = Number(docenteIdParam);
      this.cargarDatosDocente(); // Solo carga datos si es un curso existente
    }
 
}

cargarDatosDocente() {
  this.apiService.getDocenteById(this.docenteId).subscribe(docente => {
    this.docente = docente;
    this.editarDocenteForm.patchValue(docente);
  });
}

guardar() {
  if (this.editarDocenteForm.valid) {
    const docenteData = { ...this.editarDocenteForm.value };

    if (this.docenteId === 0) {
      // Crear un curso nuevo
      this.apiService.createDocente(docenteData).subscribe(() => {
        this.router.navigate(['/docentes']);
      });
    } else {
      // Actualizar un curso existente
      this.apiService.updateDocente(this.docenteId, docenteData).subscribe(() => {
        this.router.navigate(['/docentes']);
      });
    }
  }
}

volver() : void {
  this.router.navigate(['/alumnos']);
}
  
}
