package ar.edu.unju.edm.until;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.model.Pelicula;
import ar.edu.unju.edm.model.Usuario;
@Component
public class ListadoPelicula {
	 private List<Pelicula> listado = new ArrayList<>() ;
	  public ListadoPelicula() {
		// TODO Auto-generated constructor stub
	}
	public List<Pelicula> getListado() {
		return listado;
	}
	public void setListado(List<Pelicula> listado) {
		this.listado = listado;
	}
	  
}
