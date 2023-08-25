package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pelicula;

@Service
public interface IPeliculaService {
	public Pelicula nuevoPeli();
	public void guardarPelicula(Pelicula pelicula);
	public void eliminarPelicula(Integer idPelicula) throws Exception;
	public void modificarPelicula(Pelicula pelicula);
	public List<Pelicula> listarPelicula(); 
	public Pelicula buscarPelicula(Integer idPelicula) throws Exception; 
}
