package com.example.prueba.controladores;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.prueba.servicios.autorService;
import com.example.prueba.entidades.Autor;
import com.example.prueba.errores.erroresServicio;

@Controller
@RequestMapping("/autor")
public class contAutor {
//guardar/actualizar,  listar  autores,  dar de  baja. 
	
	@Autowired
	private autorService svcAutor;
	
	@GetMapping("/autores")
	public ModelAndView listarAutores(){
		ModelAndView mv = new ModelAndView("listaAutores");
		List<Autor> autor = svcAutor.consultarAutores();
		mv.addObject("autor", autor);
		return mv;
	}
	
	@GetMapping("/crear-autor")
	public ModelAndView mostrarForm(){
		return new ModelAndView ("form-autor");
	}
	
	@GetMapping("/editar-autor")
	public ModelAndView modificar(@RequestParam String nombre){
		return new ModelAndView ("editar-autor");
	}
	
	
	@PostMapping("/guardar-autor")
	public RedirectView guardarAutor(@RequestParam String nombre) throws erroresServicio{
		svcAutor.crearAutor(nombre, true);
		return new RedirectView("/autor/ver-todos");	
	}

	
}

