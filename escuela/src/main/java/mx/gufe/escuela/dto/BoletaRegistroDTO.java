package mx.gufe.escuela.dto;

import java.io.Serializable;

public class BoletaRegistroDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idAlumno;
	private Integer idCalificacion;
	private String nombre;
	private String apellido;
	private String materia;
	private Double calificacion;
	private String fecha_registro;
	
	public BoletaRegistroDTO(Integer idAlumno, Integer idCalificacion, String nombre, String apellido, String materia,
			Double calificacion, String fecha_registro) {
		super();
		this.idAlumno = idAlumno;
		this.idCalificacion = idCalificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.materia = materia;
		this.calificacion = calificacion;
		this.fecha_registro = fecha_registro;
	}

	public BoletaRegistroDTO(String nombre, String apellido, String materia, Double calificacion,
			String fecha_registro) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.materia = materia;
		this.calificacion = calificacion;
		this.fecha_registro = fecha_registro;
	}

	public BoletaRegistroDTO() {
		super();
	}

	public Integer getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}

	public Integer getIdCalificacion() {
		return idCalificacion;
	}

	public void setIdCalificacion(Integer idCalificacion) {
		this.idCalificacion = idCalificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
}
