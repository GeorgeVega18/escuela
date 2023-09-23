package mx.gufe.escuela.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.gufe.escuela.model.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {

}
