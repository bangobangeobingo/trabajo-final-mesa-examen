package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Resenia;
@Service
public interface ComentarioValoracionService {
	public Resenia nuevaResenia();
	public void guardarResenia(Resenia review);
	public List<Resenia> mostrarResenias();
}
