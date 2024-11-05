//En el contexto de Spring Data JPA, un repositorio es una interfaz que actúa como un intermediario entre la aplicación y la base de datos, permitiendo realizar operaciones de acceso a datos de manera sencilla y eficiente. Su objetivo principal es encapsular la lógica necesaria para acceder a los datos y proporcionar una forma de interactuar con las entidades.
//Este código define un repositorio en el contexto de Spring Data JPA para la entidad Estudiante.

package com.example.demo.repository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//package com.example.prueba.repository;: Esta línea declara que la clase pertenece al paquete com.example.prueba.repository. Los paquetes ayudan a organizar las clases en grupos lógicos y evitan conflictos de nombres entre diferentes clases.
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Alumno;
//import org.springframework.data.jpa.repository.JpaRepository;: Aquí se importa la interfaz JpaRepository de Spring Data JPA. Esta interfaz proporciona métodos para realizar operaciones comunes de acceso a datos, como guardar, buscar, actualizar y eliminar entidades, sin necesidad de implementar estas funcionalidades manualmente.
import com.example.demo.entity.Curso;
// Se importa la clase Estudiante del paquete com.example.Estudiante.entity. Esta clase es la entidad que el repositorio gestionará y que se mapeará a una tabla en la base de datos.
import com.example.demo.entity.Docente;

//public interface EstudianteRepository: Se declara una interfaz pública llamada EstudianteRepository. Las interfaces en Java son contratos que definen métodos que deben ser implementados por las clases que las implementan.
//La interfaz EstudianteRepository extiende JpaRepository, que es una interfaz genérica. Al hacerlo, hereda una serie de métodos predefinidos para interactuar con la base de datos:
public interface CursoRepository extends JpaRepository<Curso, Long>{

	void deleteByDocente(Optional<Docente> docente);

	List<Curso> findByDocenteId(Long id);
	
	List<Curso> findByTemaId(Long id);

	List<Curso> findByFechaFin(Date fecha);

	@Query("SELECT c FROM Curso c WHERE c.docente.id = :docenteId AND c.fechaFin > CURRENT_DATE")
	List<Curso> findCursoVigenteByDocenteId(@Param("docenteId") Long docenteId);

	Curso findCursoById(Long id);
	

	
}
