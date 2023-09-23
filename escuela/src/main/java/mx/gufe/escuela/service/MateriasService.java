package mx.gufe.escuela.service;

import java.util.List;

import mx.gufe.escuela.model.Materia;
import mx.gufe.escuela.utils.EscuelaException;

public interface MateriasService {
	List<Materia> allMaterias() throws EscuelaException;
	Materia materia(Integer idMateria) throws EscuelaException;
}
