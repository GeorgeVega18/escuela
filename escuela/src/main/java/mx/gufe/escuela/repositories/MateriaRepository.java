package mx.gufe.escuela.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.gufe.escuela.model.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {

	Optional<Materia> buscarPorNombre(String materia);
	

}
