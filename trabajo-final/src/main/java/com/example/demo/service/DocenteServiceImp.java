package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Curso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.DocenteRepository;

//Esta es una anotación de Spring que indica que la clase UsuarioServiceImp es un servicio. En el contexto de Spring, los servicios son componentes de la lógica de negocio que encapsulan la lógica relacionada con la aplicación. Spring gestionará esta clase como un componente, lo que permite la inyección de dependencias.
@Service
//Aquí se define la clase pública UsuarioServiceImp, que implementa la interfaz UsuarioService. Al implementar la interfaz, la clase UsuarioServiceImp debe proporcionar la implementación de todos los métodos definidos en UsuarioService.
public class DocenteServiceImp implements DocenteService{
//Esta anotación le dice a Spring que inyecte automáticamente una instancia de UsuarioRepository en la variable repository. Esto permite que el servicio acceda a la lógica de acceso a datos definida en el repositorio sin necesidad de crear instancias manualmente.
	@Autowired
//Se declara una variable de instancia llamada repository de tipo UsuarioRepository. Este repositorio es responsable de realizar operaciones de base de datos relacionadas con la entidad Usuario.
	DocenteRepository repository;
	
	@Autowired
	CursoService cursoService;
	
//Esta anotación indica que el método que sigue está sobrescribiendo un método de la interfaz UsuarioService. Es una buena práctica usar esta anotación para garantizar que se está sobrescribiendo correctamente un método de la interfaz.
	@Override
//Aquí se declara el método público findAll, que devuelve una lista de objetos Usuario. Este método se utiliza para obtener todos los usuarios de la base de datos.
	public List <Docente> findAll(){
//Dentro del método findAll, se llama al método findAll() del repository, que se encarga de acceder a la base de datos y recuperar todos los registros de la entidad Usuario. El resultado, que es una lista de usuarios, se devuelve al llamador de este método.
		return repository.findAll();
	}
	
	public Docente create(Docente docente) {
        return repository.save(docente);
    }
	
	public Docente updateDocente(Long id, Docente docente) {
	     // Aquí puedes implementar lógica adicional si es necesario
	     docente.setId(id); // Asegúrate de establecer el ID
	     return repository.save(docente); // Guarda el curso actualizado
	}
	
	public Optional<Docente> findById(Long id) {
        return repository.findById(id);
    }


	@Override
	public void deleteById(Long id) {
		
        
        repository.deleteById(id);
		
	}

	@Override
	public Docente findDocenteById(Long id) {
		return repository.findDocenteById(id);
	}
}
