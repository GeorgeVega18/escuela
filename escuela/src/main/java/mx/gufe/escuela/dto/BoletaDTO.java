package mx.gufe.escuela.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoletaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<BoletaRegistroDTO> calificaciones;
	private PromedioDTO promedio;
	
	public BoletaDTO(List<BoletaRegistroDTO> calificaciones, PromedioDTO promedio) {
		super();
		this.calificaciones = calificaciones;
		this.promedio = promedio;
	}
	public BoletaDTO() {
		super();
		this.calificaciones = new ArrayList<>();
		this.promedio = new PromedioDTO();
	}
	public List<BoletaRegistroDTO> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(List<BoletaRegistroDTO> calificaciones) {
		this.calificaciones = calificaciones;
	}
	public PromedioDTO getPromedio() {
		return promedio;
	}
	public void setPromedio(PromedioDTO promedio) {
		this.promedio = promedio;
	}

}
