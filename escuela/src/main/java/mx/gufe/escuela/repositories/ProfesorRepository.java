package mx.gufe.escuela.repositories;

import java.util.List;

import mx.gufe.escuela.model.Profesor;

public interface ProfesorRepository {

	List<Profesor> findAll();

	Profesor getReferenceById(Integer idprofesor);

}
