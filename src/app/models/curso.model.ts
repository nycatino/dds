// src/app/models/curso.model.ts

export interface Curso {
    id: number;
    temaId: number;
    docenteId: number;
    alumnosIds: number[];
    precio: number;
    fechaInicio: Date;
    fechaFin: Date;
  }
  