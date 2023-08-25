package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Resenia;
import ar.edu.unju.edm.repository.ComentarioValoracion;
import ar.edu.unju.edm.service.ComentarioValoracionService;

@Service
public class ComentarioValoracioServiceImp implements ComentarioValoracionService{
    @Autowired 
    Resenia resenia;
    @Autowired
    ComentarioValoracion comentarioValoracionRepository; 
	@Override
	public Resenia nuevaResenia() {
		// TODO Auto-generated method stub
		return resenia;
	}

	@Override
	public void guardarResenia(Resenia review) {
		// TODO Auto-generated method stub
		comentarioValoracionRepository.save(review);
	}

	@Override
	public List<Resenia> mostrarResenias() {
		// TODO Auto-generated method stub
		return (List<Resenia>) comentarioValoracionRepository.findAll();
	}

}
