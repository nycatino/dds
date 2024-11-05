package com.example.demo.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="alumno")

//Define la clase Estudiante. Esta clase representa la entidad que mapea la tabla estudiante en la base de datos.
public class Alumno {
//@Id: Esta anotación marca el campo legajo como la clave primaria de la entidad.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//@Column(name="nombre"): Se usa para especificar el nombre de la columna en la tabla que está asociada con el atributo nombre. En este caso, la columna en la tabla estudiante se llamará nombre.
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="fecha_nacimiento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	
	public Alumno() {
    }
	
//Este es el constructor de la clase Estudiante, que inicializa todos los atributos de la clase.
	public Alumno(Long id, String nombre, Date fecha_nacimiento) {
		this.id = id;
		this.nombre = nombre;
		this.fechaNacimiento = fecha_nacimiento;
	}
	
//Método getter para obtener el valor del atributo legajo.
	public Long getid() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getfecha_nacimiento() {
		return fechaNacimiento;
	}
	public void setfecha_nacimiento(Date fecha_nacimiento) {
		this.fechaNacimiento = fecha_nacimiento;
	}

	public void setId(Long id2) {
		this.id = id2;
	}
	
	
}