package ar.edu.unju.edm.until;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.model.Resenia;

@Component
public class ListadoResenias {
	private List <Resenia> listado = new ArrayList<>();
	
	public ListadoResenias() {
		// TODO Auto-generated constructor stub
	}
		public List<Resenia> getListado() {
		return listado;
		}
		public void setListado(List<Resenia> listado) {
		this.listado = listado;
		}
}
