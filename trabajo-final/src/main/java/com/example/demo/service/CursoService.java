package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Curso;

public interface CursoService {
	public List<Curso> findAll();
	
	public Curso create(Curso curso);

	public Curso updateCurso(Long id, Curso curso);

	public Optional<Curso> findById(Long id);

	public void deleteById(Long id);

	public List<Curso> findCursosByFechaFin(Date fecha);

	public List<Curso> findCursoVigenteByDocenteId(Long docenteId);

	public Curso findCursoById(Long id);

}
