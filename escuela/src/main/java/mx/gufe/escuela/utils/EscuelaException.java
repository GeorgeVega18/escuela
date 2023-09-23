package mx.gufe.escuela.utils;

public class EscuelaException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String description;

	public EscuelaException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EscuelaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EscuelaException(String message, String description) {
		super(message);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
