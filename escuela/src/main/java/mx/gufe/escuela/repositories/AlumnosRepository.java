package mx.gufe.escuela.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.gufe.escuela.model.Alumno;

public interface AlumnosRepository extends JpaRepository<Alumno, Integer> {

	@Query(value = "SELECT * FROM t_alumnos ta WHERE ta.nombre = ?1",nativeQuery = true)
	Optional<Alumno> buscarPorNombre(@Param("nombre") String nombre);

	@Query(value = "SELECT * FROM t_alumnos ta WHERE ta.estado = :estado", nativeQuery = true)
	List<Alumno> findAllByEstado(@Param("estado") Integer estado);

}
