package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class UsuarioPelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuarioPelicula;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private Usuario usuario;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idPelicula")
	private Pelicula pelicula;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechadeventa;
	private Boolean estado;
	
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public UsuarioPelicula() {
		// TODO Auto-generated constructor stub
	}
	public Integer getIdUsuarioPelicula() {
		return idUsuarioPelicula;
	}
	public void setIdUsuarioPelicula(Integer idUsuarioPelicula) {
		this.idUsuarioPelicula = idUsuarioPelicula;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	public LocalDate getFechadeventa() {
		return fechadeventa;
	}
	public void setFechadeventa(LocalDate fechadeventa) {
		this.fechadeventa = fechadeventa;
	}
	
}
