package ar.edu.unju.edm.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.model.UsuarioPelicula;
import ar.edu.unju.edm.service.IPeliculaService;
import ar.edu.unju.edm.service.IUsuarioPeliculaService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UsuarioPeliculaController {
	private static final Log GRUPO2 = LogFactory.getLog(UsuarioController.class);

	@Autowired
    IUsuarioPeliculaService usuarioPeliculaService;
	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	IPeliculaService peliculaService;
	@GetMapping("/nuevaTicket")
	public ModelAndView addInscripcion(Principal principal) throws Exception {
		Usuario existente = usuarioService.encontrarConCorreo(principal.getName());
		
	GRUPO2.info("ingresando al metodo: bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		ModelAndView modelView = new ModelAndView("ticket");
		modelView.addObject("unTicket", usuarioPeliculaService.nuevoUsuarioPelicula());
		//modelView.addObject("usuarios", usuarioService.mostrarUsuario());
		modelView.addObject("usuarios", existente);
		modelView.addObject("pelicula", peliculaService.listarPelicula());
		return modelView;
	}

	@PostMapping("/guardarTicket")
	public ModelAndView saveInscripcion(@Valid @ModelAttribute("unTicket") UsuarioPelicula usuarioPeliculaNuevo, BindingResult resultado) {			
			// VERIFICACION DEL NOMBRE Y DNI	
		ModelAndView modelView = new ModelAndView();
		if (resultado.hasErrors()) {
			GRUPO2.fatal("ERROR DE VALIDACION");			
			modelView.setViewName("ticket");
			modelView.addObject("unTicket", usuarioPeliculaNuevo);			
			return modelView;
		}		
		try {
			usuarioPeliculaService.guardarUsuarioPelicula(usuarioPeliculaNuevo);
		} catch (Exception e) {			
			modelView.addObject("formUsuarioErrorMessage", e.getMessage());
			modelView.addObject("unTicket", usuarioPeliculaService.nuevoUsuarioPelicula());
			GRUPO2.error("saliendo del metodo: eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			modelView.setViewName("ticket");
			return modelView;		
		}		
		
		modelView.addObject("formUsuarioErrorMessage", "Usuario guardado correctamente");
		modelView.addObject("unTicket", usuarioPeliculaService.nuevoUsuarioPelicula());
		//modelView.setViewName("ticket");
		modelView.addObject("listaTickets", usuarioPeliculaService.listarUsuariosPelicula());
		modelView.setViewName("listadoTickets");
		return modelView;
		}
	@GetMapping("/listadoTickets")	
	public ModelAndView showCourses() {
		ModelAndView vista = new ModelAndView("listadoTickets");		
		vista.addObject("listaTickets", usuarioPeliculaService.listarUsuariosPelicula());		
		return vista;
	}
}
