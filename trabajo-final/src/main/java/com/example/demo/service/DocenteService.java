package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Curso;
import com.example.demo.entity.Docente;

public interface DocenteService {
	public List<Docente> findAll();
	
	public Docente create(Docente docente);
	
	public Docente updateDocente(Long id, Docente docente);
	
	public Optional<Docente> findById(Long id);

	public void deleteById(Long id);
	
	public Docente findDocenteById(Long id);
}
