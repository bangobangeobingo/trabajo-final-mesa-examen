package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.UsuarioPelicula;

@Service
public interface IUsuarioPeliculaService {
	public UsuarioPelicula nuevoUsuarioPelicula();
	public void guardarUsuarioPelicula(UsuarioPelicula usuarioPelicula);
	public void eliminarUsuarioPelicula(Integer id) throws Exception;
	public void modificarUsuarioPelicula(UsuarioPelicula usuarioPelicula);
	public List<UsuarioPelicula> listarUsuariosPelicula(); 
	public UsuarioPelicula buscarUsuarioPelicula(Integer id) throws Exception;         
}
