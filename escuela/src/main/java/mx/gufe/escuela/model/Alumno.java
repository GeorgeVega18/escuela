package mx.gufe.escuela.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_alumnos")
public class Alumno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_usuarios")
    private Integer id;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "ap_paterno")
	private String apPaterno;

	@Column(name = "ap_materno")
	private String apMaterno;
	
	@Column(name = "activo")
	private Long activo;

	public Alumno(Integer id, String name, String apPaterno, String apMaterno, Long activo) {
		super();
		this.id = id;
		this.name = name;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.activo = activo;
	}

	public Alumno(String name, String apPaterno, String apMaterno, Long activo) {
		super();
		this.name = name;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.activo = activo;
	}

	public Alumno() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public Long getActivo() {
		return activo;
	}

	public void setActivo(Long activo) {
		this.activo = activo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(activo, apMaterno, apPaterno, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(activo, other.activo) && Objects.equals(apMaterno, other.apMaterno)
				&& Objects.equals(apPaterno, other.apPaterno) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", name=" + name + ", apPaterno=" + apPaterno + ", apMaterno=" + apMaterno
				+ ", activo=" + activo + "]";
	}

	
	
}
