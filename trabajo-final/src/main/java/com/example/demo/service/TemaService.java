package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Docente;
import com.example.demo.entity.Tema;

public interface TemaService {
	public List<Tema> findAll();
	
	public Tema create(Tema tema);
	
	public Tema updateTema(Tema tema);
	
	public Tema findTemaById(Long id);
	
	public void deleteById(Long id);

	public Optional<Tema> findById(Long id);

}
