package com.example.prueba.repositorios;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.prueba.entidades.Autor;

@Repository
public interface repoAutor extends JpaRepository<Autor, String>{
	
	@Query("SELECT a FROM Autor a WHERE a.alta = true")
	public List<Autor> buscarActivos();
	
	@Query("SELECT a FROM Autor a WHERE a.nombre LIKE: nombre")
	public List<Autor> buscarNombres(@Param("nombre") String nombre);
}