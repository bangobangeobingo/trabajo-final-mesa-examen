package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Pelicula;

@Repository
public interface PeliculaRepository extends CrudRepository <Pelicula,Integer>{
	// recuperar los cursos con estado en verdadero
		public List<Pelicula> findByEstado(Boolean estadoCurso);
}
