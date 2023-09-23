package mx.gufe.escuela.dto;

import java.io.Serializable;

public class CalificacionDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer idCalificacion;
	private Integer idAlumno;
	private Integer idMateria;
	private Double calificacion;
	
	public CalificacionDTO(Integer idAlumno, Integer idMateria, Double calificacion) {
		super();
		this.idAlumno = idAlumno;
		this.idMateria = idMateria;
		this.calificacion = calificacion;
	}

	public CalificacionDTO(Integer idCalificacion, Integer idAlumno, Integer idMateria, Double calificacion) {
		super();
		this.idCalificacion = idCalificacion;
		this.idAlumno = idAlumno;
		this.idMateria = idMateria;
		this.calificacion = calificacion;
	}
	
	public CalificacionDTO() {
		super();
	}
	
	public Integer getIdCalificacion() {
		return idCalificacion;
	}

	public void setIdCalificacion(Integer idCalificacion) {
		this.idCalificacion = idCalificacion;
	}

	public Integer getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}

	public Integer getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}
}
