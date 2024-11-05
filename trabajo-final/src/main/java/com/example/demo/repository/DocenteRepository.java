//En el contexto de Spring Data JPA, un repositorio es una interfaz que actúa como un intermediario entre la aplicación y la base de datos, permitiendo realizar operaciones de acceso a datos de manera sencilla y eficiente. Su objetivo principal es encapsular la lógica necesaria para acceder a los datos y proporcionar una forma de interactuar con las entidades.
//Este código define un repositorio en el contexto de Spring Data JPA para la entidad Estudiante.

package com.example.demo.repository;
//package com.example.prueba.repository;: Esta línea declara que la clase pertenece al paquete com.example.prueba.repository. Los paquetes ayudan a organizar las clases en grupos lógicos y evitan conflictos de nombres entre diferentes clases.
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Curso;
// Se importa la clase Estudiante del paquete com.example.Estudiante.entity. Esta clase es la entidad que el repositorio gestionará y que se mapeará a una tabla en la base de datos.
import com.example.demo.entity.Docente;

//public interface EstudianteRepository: Se declara una interfaz pública llamada EstudianteRepository. Las interfaces en Java son contratos que definen métodos que deben ser implementados por las clases que las implementan.
//La interfaz EstudianteRepository extiende JpaRepository, que es una interfaz genérica. Al hacerlo, hereda una serie de métodos predefinidos para interactuar con la base de datos:
public interface DocenteRepository extends JpaRepository<Docente, Long>{
	
	Docente findDocenteById(Long id);
}
