package mx.gufe.escuela.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_calificaciones")
public class Calificacion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_calificaciones")
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_t_materias")
    private Materia materia;
	
	@ManyToOne
	@JoinColumn(name = "id_t_usuarios")
    private Alumno alumno;
	
	@Column(name = "calificacion")
    private Double calificacion;

	@Column(name = "fecha_registro")
    private Date fechaRegistro;

	public Calificacion(Integer id, Materia materia, Alumno alumno, Double calificacion, Date fechaRegistro) {
		super();
		this.id = id;
		this.materia = materia;
		this.alumno = alumno;
		this.calificacion = calificacion;
		this.fechaRegistro = fechaRegistro;
	}

	public Calificacion(Materia materia, Alumno alumno, Double calificacion, Date fechaRegistro) {
		super();
		this.materia = materia;
		this.alumno = alumno;
		this.calificacion = calificacion;
		this.fechaRegistro = fechaRegistro;
	}

	public Calificacion() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alumno, calificacion, fechaRegistro, id, materia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calificacion other = (Calificacion) obj;
		return Objects.equals(alumno, other.alumno) && Objects.equals(calificacion, other.calificacion)
				&& Objects.equals(fechaRegistro, other.fechaRegistro) && Objects.equals(id, other.id)
				&& Objects.equals(materia, other.materia);
	}

	@Override
	public String toString() {
		return "Calificacion [id=" + id + ", materia=" + materia.toString() + ", alumno=" + alumno.toString() + ", calificacion="
				+ calificacion + ", fechaRegistro=" + fechaRegistro + "]";
	}

		
}
