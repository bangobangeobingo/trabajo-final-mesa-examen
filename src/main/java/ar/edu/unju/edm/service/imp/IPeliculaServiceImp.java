package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pelicula;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.PeliculaRepository;
import ar.edu.unju.edm.service.IPeliculaService;
import ar.edu.unju.edm.until.ListadoPelicula;
import ch.qos.logback.core.read.ListAppender;
@Service
public class IPeliculaServiceImp implements IPeliculaService {
    @Autowired
    Pelicula nuevopeli;
    @Autowired
    ListadoPelicula lista;
    @Autowired
    PeliculaRepository peliRepository;
	@Override
	public Pelicula nuevoPeli() {
		// TODO Auto-generated method stub
		return nuevopeli;
	}

	@Override
	public void guardarPelicula(Pelicula pelicula) {
		// TODO Auto-generated method stub
		//pelicula.setEstado(true);
		peliRepository.save(pelicula);
	}

	@Override
	public void eliminarPelicula(Integer idPelicula) throws Exception {
		// TODO Auto-generated method stub
		Pelicula auxiliar =new Pelicula();
		auxiliar=buscarPelicula(idPelicula);
		auxiliar.setEstado(false);
		peliRepository.save(auxiliar);
	}

	@Override
	public void modificarPelicula(Pelicula pelicula) {
		// TODO Auto-generated method stub
		peliRepository.save(pelicula);
	}

	@Override
	public List<Pelicula> listarPelicula() {
		List<Pelicula> auxiliar = new ArrayList<>();
		List<Pelicula> auxiliar2 = new ArrayList<>();
		auxiliar=(List<Pelicula>) peliRepository.findAll();
		for(int i = 0 ;i<auxiliar.size();i++) {
			//SANTI.error("recorr;SSSS"+lista.getListado().get(i).getDni());
			if (auxiliar.get(i).getEstado()==true) {
				auxiliar2.add(auxiliar.get(i));
			}
		}	
		return auxiliar2;
	}

	@Override
	public Pelicula buscarPelicula(Integer idPelicula) throws Exception {
		// TODO Auto-generated method stub
		Pelicula peliEncontrado =new Pelicula();
		for(int i =0 ; i<lista.getListado().size();i++) {
			//SANTI.error("recorriendo:Aaaaaaaaa"+id);
			if(lista.getListado().get(i).getIdPelicula().equals(idPelicula)) {
				peliEncontrado= lista.getListado().get(i);
			}
		}
		peliEncontrado=peliRepository.findById(idPelicula).orElseThrow(()->new Exception("usuario no encontrado"));
		return peliEncontrado;
	}



}
