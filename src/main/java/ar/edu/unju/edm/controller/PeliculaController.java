package ar.edu.unju.edm.controller;

import java.util.Base64;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Pelicula;
import ar.edu.unju.edm.service.IPeliculaService;

@Controller
public class PeliculaController {
private static final Log SANTI = LogFactory.getLog(UsuarioController.class);
	@Autowired
	Pelicula newpeli;
	@Autowired
	IPeliculaService peliService;
	
	@GetMapping("/nuevoPeli")
	public ModelAndView addCurso() {
		SANTI.info("ingresando al metodo: bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		ModelAndView modelView = new ModelAndView("cargarPelicula");
		modelView.addObject("unaPeli", peliService.nuevoPeli());
		modelView.addObject("editMode", false);
		return modelView;
	}

	@PostMapping(value="/guardarPeli", consumes = "multipart/form-data" )
	public ModelAndView saveCurso(@Valid @ModelAttribute("unaPeli") Pelicula peliNuevo, BindingResult resultado, @RequestParam("file") MultipartFile file) {			
		ModelAndView modelView = new ModelAndView();
		if (resultado.hasErrors()) {
		     SANTI.fatal("ERROR DE VALIDACION");			
			modelView.setViewName("cargarPelicula");
			modelView.addObject("unaPeli", peliNuevo);			
			return modelView;
		}		
		try {
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			peliNuevo.setImagen(base64);
			peliNuevo.setEstado(true);
			peliService.guardarPelicula(peliNuevo);
		} catch (Exception e) {			
			modelView.addObject("formCursoErrorMessage", e.getMessage());
			modelView.addObject("unaPeli", peliService.nuevoPeli());
			SANTI.error("saliendo del metodo: eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			modelView.setViewName("cargarPelicula");
			return modelView;		
		}		
		
		modelView.addObject("formCursoErrorMessage", "Curso guardado correctamente");
		modelView.addObject("unaPeli", peliService.nuevoPeli());
		modelView.addObject("listadoPeliculas", peliService.listarPelicula());
		modelView.setViewName("cargarPelicula");
		modelView.addObject("editMode", false);
		return modelView;
		}
	
	@GetMapping("/listadoPeliculas")	
	public ModelAndView showCourses() {
		ModelAndView vista = new ModelAndView("listadoPeliculas");		
		vista.addObject("listaPelicula", peliService.listarPelicula());		
		return vista;
	}
	@GetMapping("/editarPelicula/{idPelicula}")
	public ModelAndView getFormEditpeli(Model model, @PathVariable(name = "idPelicula") Integer idPelicula) throws Exception{
		Pelicula cursoEncontrado = new Pelicula();
		try {
			cursoEncontrado = peliService.buscarPelicula(idPelicula);
			
		} catch (Exception e) {
			model.addAttribute("formCursoErrorMessage", e.getMessage());
		}
		ModelAndView modelView = new ModelAndView("cargarPelicula");
		modelView.addObject("unaPeli", cursoEncontrado);
		//rescatarId=cursoEncontrado.getId();
		SANTI.error("Saliendo del metodo: getFormEditCourse "+cursoEncontrado.getIdPelicula());
		
		modelView.addObject("editMode", true);
	
		return modelView;
	}

	@PostMapping("/editarPelicula")
	public ModelAndView postEditpeli(@ModelAttribute("cursoF") Pelicula peliModificado,BindingResult result){
	//	SANTI.info("El id del curso ha modiicar es: "+rescatarId);
		//cursoModificado.setId(rescatarId);
		if(result.hasErrors()) {
			SANTI.fatal("Error de validadcion");
			ModelAndView vista= new ModelAndView("cargarPelicula");
			vista.addObject("unaPeli",peliModificado);
			vista.addObject("editMode",true);
			return vista;
		}
		try {
			peliService.modificarPelicula(peliModificado);
			
		}
		catch(Exception e){
			ModelAndView vista= new ModelAndView("cargarPelicula");
			vista.addObject("formCursoErrorMessage",e.getMessage());
			vista.addObject("unaPeli",peliModificado);
			vista.addObject("editMode",true);
			return vista;
		}
		ModelAndView vista= new ModelAndView("/listadoPeliculas");
		vista.addObject("listadoPeliculas", peliService.listarPelicula());		
		vista.addObject("formCursoErrorMessage", "Curso guardado Correctamente");
		return vista;
		/*
		cursoService.modficarCurso(cursoModificado);
		ModelAndView modelView = new ModelAndView("mostrarCursos");
		modelView.addObject("ListadoCursos", cursoService.listarCursos());
		modelView.addObject("formCourseErrorMessage", "Curso guardado Correctamente");
//		SANTI.info("Curso modificado guardado correctamente");

		return modelView;*/
	}

	@GetMapping("/deletePelicula/{idPelicula}")
	public String eliminar(@PathVariable Integer idPelicula, Model model){
		try {
			peliService.eliminarPelicula(idPelicula);
		} catch (Exception e) {
			SANTI.error("Encontrando Curso");
			model.addAttribute("formCursoErrorMessage", e.getMessage());
			return "redirect:/newpeli";
		}
		return "redirect:/listadoPeliculas";
	}
}
