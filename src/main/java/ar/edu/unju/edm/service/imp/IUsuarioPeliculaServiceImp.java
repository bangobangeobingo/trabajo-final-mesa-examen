package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pelicula;
import ar.edu.unju.edm.model.UsuarioPelicula;
import ar.edu.unju.edm.repository.UsuarioPeliculaRepository;
import ar.edu.unju.edm.service.IUsuarioPeliculaService;
import ar.edu.unju.edm.until.ListadoUsuarioPelicula;

@Service
public class IUsuarioPeliculaServiceImp implements IUsuarioPeliculaService{
	@Autowired
	ListadoUsuarioPelicula lista;
    @Autowired
    UsuarioPelicula usuariopelicula;
    @Autowired 
    UsuarioPeliculaRepository usuarioPelicularepository;
	@Override
	public UsuarioPelicula nuevoUsuarioPelicula() {
		// TODO Auto-generated method stub
		return usuariopelicula;
	}

	@Override
	public void guardarUsuarioPelicula(UsuarioPelicula usuarioPelicula) {
		// TODO Auto-generated method stub
		usuarioPelicula.setEstado(true);
		usuarioPelicularepository.save(usuarioPelicula);
	}

	@Override
	public void eliminarUsuarioPelicula(Integer id) throws Exception {
		// TODO Auto-generated method stub
		UsuarioPelicula auxiliar =new UsuarioPelicula();
		auxiliar=buscarUsuarioPelicula(id);
		auxiliar.setEstado(false);
		usuarioPelicularepository.save(auxiliar);
	}

	@Override
	public void modificarUsuarioPelicula(UsuarioPelicula usuarioPelicula) {
		// TODO Auto-generated method stub
		usuarioPelicularepository.save(usuarioPelicula);
	}

	@Override
	public List<UsuarioPelicula> listarUsuariosPelicula() {
		// TODO Auto-generated method stub
		List<UsuarioPelicula> auxiliar = new ArrayList<>();
		List<UsuarioPelicula> auxiliar2 = new ArrayList<>();
		auxiliar=(List<UsuarioPelicula>) usuarioPelicularepository.findAll();
		for(int i = 0 ;i<auxiliar.size();i++) {
			//SANTI.error("recorr;SSSS"+lista.getListado().get(i).getDni());
			if (auxiliar.get(i).getEstado()==true) {
				auxiliar2.add(auxiliar.get(i));
			}
		}	
		return auxiliar2;
	}

	@Override
	public UsuarioPelicula buscarUsuarioPelicula(Integer id) throws Exception {
		// TODO Auto-generated method stub
		UsuarioPelicula peliEncontrado =new UsuarioPelicula();
		for(int i =0 ; i<lista.getListado().size();i++) {
			//SANTI.error("recorriendo:Aaaaaaaaa"+id);
			if(lista.getListado().get(i).getIdUsuarioPelicula().equals(peliEncontrado)) {
				peliEncontrado= lista.getListado().get(i);
			}
		}
		peliEncontrado=usuarioPelicularepository.findById(id).orElseThrow(()->new Exception("usuario no encontrado"));
		return peliEncontrado;
	}

}