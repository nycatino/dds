package com.example.demo.controller;
//Esta es una anotación de Spring que indica que esta clase es un controlador REST. Significa que la clase manejará solicitudes HTTP y devolverá respuestas en formato JSON (o XML, dependiendo de la configuración), facilitando la creación de servicios web RESTful.
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DocenteService;
import com.example.demo.service.TemaService;
import com.example.demo.entity.Alumno;
import com.example.demo.entity.Docente;
import com.example.demo.entity.Tema;
import com.example.demo.repository.TemaRepository;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
//Se declara una clase pública llamada UsuarioController. Este controlador se encargará de manejar las solicitudes relacionadas con la entidad Estudiante.
public class TemaController {
	//@Autowired: Esta anotación indica que Spring debe inyectar automáticamente una instancia del tipo EstudianteService en esta variable. Esto permite que el controlador utilice los métodos del servicio para realizar operaciones relacionadas con Estudiante.
	//EstudianteService service;: Se declara una variable de instancia service de tipo EstudianteService. Esta variable contendrá la lógica de negocio relacionada con los estudiantes, que se encapsula en el servicio.
	@Autowired
	TemaService service;
	//@GetMapping("/"): Esta anotación se utiliza para mapear solicitudes HTTP GET a la ruta raíz (/). Indica que el método siguiente debe ejecutarse cuando se recibe una solicitud GET en esta ruta.
	//GetMapping("/Estudiante"): Esta anotación se utiliza para mapear solicitudes HTTP GET a la ruta /Estudiante. Indica que el método siguiente se ejecutará cuando se reciba una solicitud GET en esta ruta.
	@CrossOrigin
	@GetMapping
	//Se declara un método público llamado findEstudiantes que no recibe parámetros y devuelve una lista de objetos Estudiante. Este método manejará las solicitudes a la ruta /Estudiante.
	//ste código llama al método findAll() del servicio EstudianteService, que se espera que devuelva una lista de todos los estudiantes en la base de datos. La lista se devuelve como respuesta a la solicitud GET en la ruta /Estudiante
	public List<Tema> findTemas(){
		return service.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<Tema> findTemaById(@PathVariable Long id) {
	    Tema tema = service.findTemaById(id);
	    return ResponseEntity.ok(tema);
	}
	
	@CrossOrigin
	@PostMapping
    public ResponseEntity<Tema> createTema(@RequestBody Tema tema) {
        Tema nuevoTema = service.create(tema); // Llama al servicio para crear el alumno
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTema); // Retorna respuesta con el nuevo alumno y estado 201
    }
	@CrossOrigin
	@PutMapping
    public ResponseEntity<Tema> updateTema( @RequestBody Tema tema) {
		Tema updatedTema = service.updateTema(tema);
        return ResponseEntity.ok(updatedTema);
    }
	@CrossOrigin
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
