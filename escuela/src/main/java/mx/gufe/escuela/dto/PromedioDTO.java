package mx.gufe.escuela.dto;

import java.io.Serializable;

public class PromedioDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Double promedio;

	public PromedioDTO(Double promedio) {
		super();
		this.promedio = promedio;
	}

	public PromedioDTO() {
		super();
	}

	public Double getPromedio() {
		return promedio;
	}

	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}
}
