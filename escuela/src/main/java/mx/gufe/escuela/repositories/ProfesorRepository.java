package mx.gufe.escuela.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import mx.gufe.escuela.model.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer>{

	@Query(value = "SELECT * FROM t_Profesor ta WHERE ta.estado = :estado", nativeQuery = true)
	List<Profesor> findAllByEstado(@Param("estado") Integer estado);List<Profesor> findAll();

	Profesor getReferenceById(Integer idprofesor);

}
