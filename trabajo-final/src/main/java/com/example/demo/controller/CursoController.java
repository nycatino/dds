package com.example.demo.controller;
//Esta es una anotación de Spring que indica que esta clase es un controlador REST. Significa que la clase manejará solicitudes HTTP y devolverá respuestas en formato JSON (o XML, dependiendo de la configuración), facilitando la creación de servicios web RESTful.

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CursoService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.demo.entity.Alumno;
import com.example.demo.entity.Curso;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cursos")
//Se declara una clase pública llamada UsuarioController. Este controlador se encargará de manejar las solicitudes relacionadas con la entidad Estudiante.
public class CursoController {
	//@Autowired: Esta anotación indica que Spring debe inyectar automáticamente una instancia del tipo EstudianteService en esta variable. Esto permite que el controlador utilice los métodos del servicio para realizar operaciones relacionadas con Estudiante.
	//EstudianteService service;: Se declara una variable de instancia service de tipo EstudianteService. Esta variable contendrá la lógica de negocio relacionada con los estudiantes, que se encapsula en el servicio.
	@Autowired
    private CursoService service;
	@CrossOrigin
    @GetMapping
	//Se declara un método público llamado findEstudiantes que no recibe parámetros y devuelve una lista de objetos Estudiante. Este método manejará las solicitudes a la ruta /Estudiante.
	//ste código llama al método findAll() del servicio EstudianteService, que se espera que devuelva una lista de todos los estudiantes en la base de datos. La lista se devuelve como respuesta a la solicitud GET en la ruta /Estudiante
	public List<Curso> findCursos(){
		return service.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<Curso> findCursoById(@PathVariable Long id) {
	    Curso curso = service.findCursoById(id);
	    return ResponseEntity.ok(curso);
	}

	
	@CrossOrigin
	 @PostMapping
	    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {

	        // Guardar el curso usando el servicio
	        Curso nuevoCurso = service.create(curso);

	        return ResponseEntity.ok(nuevoCurso);
	    }
	@CrossOrigin
	 @PutMapping("/{id}")
	    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
	        Curso updatedCurso = service.updateCurso(id, curso);
	        return ResponseEntity.ok(updatedCurso);
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
	
	@CrossOrigin
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	 @GetMapping("/finalizan")
	    public ResponseEntity<List<Curso>> getCursosPorFecha(@RequestParam("fecha") Date fecha) {
	        List<Curso> cursos = service.findCursosByFechaFin(fecha);
	        if (cursos.isEmpty()) {
	            return ResponseEntity.noContent().build(); // Devuelve 204 No Content si no hay cursos
	        }
	        return ResponseEntity.ok(cursos); // Devuelve 200 OK con los cursos
	    }
		
	 // Endpoint para obtener los alumnos del curso vigente de un docente
	@CrossOrigin
	 @GetMapping("/docente/{docenteId}/alumnos-vigentes")
    public ResponseEntity<List<Curso>> findCursoVigenteByDocente(@PathVariable("docenteId") Long docenteId) {
        List<Curso> cursos = service.findCursoVigenteByDocenteId(docenteId);
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content si no hay cursos
        }
        return ResponseEntity.ok(cursos);
	 }
}

