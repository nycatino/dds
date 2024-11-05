import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';
import { Tema } from '../../models/tema.model';

@Component({
  selector: 'app-tema-form',
  standalone: true,
  imports: [FormsModule, RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './tema-form.component.html',
  styleUrl: './tema-form.component.css'
})
export class TemaFormComponent {

  editarTemaForm: FormGroup;
  temaId: number = 0;
  tema: Tema | undefined; 

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private apiService: ApiService
  ) {
    this.editarTemaForm = this.fb.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
    });
  }

  ngOnInit(): void {

    const temaIdParam = this.route.snapshot.params['id'];
    if (temaIdParam === 'nuevo') {
      this.temaId = 0; // Indica un nuevo curso
    } else {
      this.temaId = Number(temaIdParam);
      this.cargarDatosTema(); // Solo carga datos si es un curso existente
    }
 
}

cargarDatosTema() {
  this.apiService.getTemaById(this.temaId).subscribe(tema => {
    this.tema = tema;
    this.editarTemaForm.patchValue(tema);
  });
}

guardar() {
  if (this.editarTemaForm.valid) {
    const temaData = { ...this.editarTemaForm.value };

    if (this.temaId === 0) {
      // Crear un curso nuevo
      this.apiService.createTema(temaData).subscribe(() => {
        this.router.navigate(['/temas']);
      });
    } else {
      // Actualizar un curso existente
      this.apiService.updateTema(this.temaId, temaData).subscribe(() => {
        this.router.navigate(['/temas']);
      });
    }
  }
}
volver() : void {
  this.router.navigate(['/temas']);
}
}
