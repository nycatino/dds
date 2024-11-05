package com.example.demo.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="curso")
//Define la clase Estudiante. Esta clase representa la entidad que mapea la tabla estudiante en la base de datos.
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // O @GeneratedValue(strategy = GenerationType.AUTO) según tu caso
	private Long id;
//@Id: Esta anotación marca el campo legajo como la clave primaria de la entidad.
	@ManyToOne
	@JoinColumn(name = "tema_id")
	private Tema tema;
//@Column(name="nombre"): Se usa para especificar el nombre de la columna en la tabla que está asociada con el atributo nombre. En este caso, la columna en la tabla estudiante se llamará nombre.
//Esta anotación define la relación de muchos a muchos entre dos entidades. Indica que una entidad puede estar relacionada con muchas otras instancias de otra entidad y viceversa.
//Por ejemplo, si tienes una entidad Curso, un curso puede tener muchos alumnos, y cada alumno puede estar inscrito en muchos cursos. Esto crea una relación "muchos a muchos".
//Esta anotación se utiliza para especificar detalles sobre la tabla de unión que se crea para la relación muchos a muchos. Define el nombre de la tabla intermedia que se utilizará para vincular las dos tablas que representan las entidades relacionadas (Curso y Alumno).
	@ManyToMany
	@JoinTable(
			name = "curso_alumno", 
			joinColumns = @JoinColumn(name = "curso_id"), 
			inverseJoinColumns = @JoinColumn(name = "alumno_id")
	)
//Este atributo especifica el nombre de la tabla de unión (o tabla intermedia). En este caso, la tabla se llamará curso_alumno. Esta tabla almacenará los pares de identificadores (curso_id y alumno_id) que representan las relaciones entre cursos y alumnos.
//Este atributo define la columna en la tabla de unión que hace referencia a la entidad actual, es decir, la entidad en la que se coloca esta anotación (suponiendo que es Curso). En este caso, la columna se llamará curso_id y contendrá las claves foráneas (FK) que apuntan a los identificadores de los cursos.
//Este atributo define la columna en la tabla de unión que hace referencia a la otra entidad involucrada en la relación muchos a muchos, en este caso, la entidad Alumno. La columna se llamará alumno_id y contendrá las claves foráneas (FK) que apuntan a los identificadores de los alumnos.
	private List<Alumno> alumnos;
	
	@Column(name="fecha_inicio")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaInicio;
	@Column(name="fecha_fin")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaFin;
	@ManyToOne
	@JoinColumn(name="docente_id")
	private Docente docente;
	@Column(name="precio")
	private Double precio;
	
	
	public Curso() {
    }
	
//Este es el constructor de la clase Estudiante, que inicializa todos los atributos de la clase.
	public Curso(Tema tema, Date fecha_inicio, Date fecha_fin, Docente docente, Double precio, List<Alumno> alumnos) {
		this.tema = tema;
		this.fechaInicio = fecha_inicio;
		this.fechaFin = fecha_fin;
		this.docente = docente;
		this.precio = precio;
		this.alumnos = alumnos;
	}
	
//Método getter para obtener el valor del atributo legajo.
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos (List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public void setId(Long id) {
		this.id = id;
		
	}
	
	public Long getId() {
		return id;
	}
	
}