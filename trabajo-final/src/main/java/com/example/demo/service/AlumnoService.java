package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Docente;

public interface AlumnoService {
	public List<Alumno> findAll();
	
	public Alumno create(Alumno alumno);
	
	public Alumno updateAlumno(Long id, Alumno alumno);
	
	public Optional<Alumno> findById(Long id);
	
	public void deleteById(Long id);
	
	public Alumno findAlumnoById(Long id);
}
