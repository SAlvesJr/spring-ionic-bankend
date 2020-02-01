package com.SAlvesjr.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SAlvesjr.cursomc.domain.Categoria;
import com.SAlvesjr.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	CategoriaService categoria;

	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias() {
		Categoria cat1 = new Categoria(1L, "Informática");
		Categoria cat2 = new Categoria(2L, "Escritório");
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		return ResponseEntity.ok(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Long id) {
		Categoria obj = categoria.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
