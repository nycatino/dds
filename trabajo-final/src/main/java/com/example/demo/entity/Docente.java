package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="docente")
//Define la clase Estudiante. Esta clase representa la entidad que mapea la tabla estudiante en la base de datos.
public class Docente {
//@Id: Esta anotación marca el campo legajo como la clave primaria de la entidad.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "legajo")
    private Long legajo;
	@Column(name="nombre")
	private String nombre;
//@Column(name="nombre"): Se usa para especificar el nombre de la columna en la tabla que está asociada con el atributo nombre. En este caso, la columna en la tabla estudiante se llamará nombre.
	
	public Docente() {
    }
	
//Este es el constructor de la clase Estudiante, que inicializa todos los atributos de la clase.
	public Docente(String nombre, Long legajo) {
		this.nombre = nombre;
		this.legajo = legajo;
	}
	
//Método getter para obtener el valor del atributo legajo.
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getLegajo() {
		return legajo;
	}
	public void setLegajo(Long legajo) {
		this.legajo = legajo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id2) {
		this.id = id2;	
	}
	
}