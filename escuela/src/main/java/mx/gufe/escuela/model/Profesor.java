package mx.gufe.escuela.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_profesor")
public class Profesor implements  Serializable{
	
	private static final long serialVersionUID1 = 1L;

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
	
	@ManyToOne
	@JoinColumn(name = "id_t_materias")
    private Materia materia;
	
	public Profesor(Integer id, String name, String apPaterno, String apMaterno, Materia materia) {
		super();
		this.id = id;
		this.name = name;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.materia = materia;
	}


	private static final long serialVersionUID = 1L;
		private String nombre;
	    private String apellido;
	    

	    public String getNombre() {
	        return nombre;
	    }

	    public String getApellido() {
	        return apellido;
	    }

	    public void darClase(String materia) {
	        System.out.println(nombre + " está dando clases de " + materia);
	    }


     @Override
    public String toString() {
	return "Profesor [id=" + id + ", name=" + name + ", apPaterno=" + apPaterno + ", apMaterno=" + apMaterno
			+ ", materia=" + materia + "]";
        }
}