package ar.edu.unju.edm.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Pelicula;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IPeliculaService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UserPeliculaController {
	private static final Log GRUPO2 = LogFactory.getLog(UsuarioController.class);

	@Autowired
	IPeliculaService peliService;
	@Autowired 
	IUsuarioService userService;
	@Autowired
	Pelicula peliSelecionado;
	
	@GetMapping("/formulario/pelicula")
	public String cargarPoi(Model model) {
		model.addAttribute("ListarPeli", peliService.listarPelicula());
	
		return("pelicula");
	}
   
}
