package mx.gufe.escuela.service;

import java.util.List;

import mx.gufe.escuela.model.Profesor;
import mx.gufe.escuela.utils.EscuelaException;

public interface ProfesorService {
	List<Profesor> allProfesor() throws EscuelaException;
	Profesor profesor(Integer idProfesor) throws EscuelaException;

	 Profesor agregarProfesor (Profesor agregarProfesor);
	 Profesor buscaPorId(Integer idProfesor);
	Boolean eliminarProfesorPorId(Integer id);


}
