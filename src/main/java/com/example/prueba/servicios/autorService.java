package com.example.prueba.servicios;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba.entidades.Autor;
import com.example.prueba.errores.erroresServicio;
import com.example.prueba.repositorios.repoAutor;

@Service
public class autorService {
	
	@Autowired
	private repoAutor repositorioAutor;
	
	@Transactional(readOnly=true) 
	public List<Autor> consulta (String nombre) {
		List <Autor> listaAutores;
		listaAutores = repositorioAutor.buscarNombres(nombre);
		return listaAutores;
	} 
	
	@Transactional
	public void crearAutor (String nombre, Boolean alta) throws erroresServicio {
		validar(nombre);
		
		Autor autor = new Autor ();
		autor.setNombre(nombre);
		autor.setAlta(alta);
		
		repositorioAutor.save(autor);
	}
	
	@Transactional
	public void modificar (String id, String nombre, Boolean alta) throws erroresServicio {
		validar(nombre);
		Optional <Autor> respuesta = repositorioAutor.findById(id);
		if (respuesta.isPresent()) {
			Autor autor = respuesta.get();
			autor.setNombre(nombre);	
			repositorioAutor.save(autor);
		} else  {
			throw new erroresServicio ("No se encontró el autor solicitado.");
		}	
	}
	
	@Transactional
	public void altaBaja (String id, Boolean alta) throws erroresServicio {
		Optional <Autor> respuesta = repositorioAutor.findById(id);
		if (respuesta.isPresent()) {
			Autor autor = respuesta.get();
			autor.setAlta(alta);
			repositorioAutor.save(autor);
		} else  {
			throw new erroresServicio ("No se encontró el autor solicitado.");
		}	
		
	
	}
	
	private void validar (String nombre) throws erroresServicio{
		if	(nombre == null || nombre.isEmpty()){
			throw new erroresServicio ("El nombre del autor no puede ser nulo");
		}
	}
	
	//CONSULTAR TODOS LOS AUTORES
	@Transactional(readOnly=true) 
	public List<Autor> consultarAutores () {
		List <Autor> listaAutores;
		listaAutores = repositorioAutor.findAll();
		return listaAutores;
	} 
}