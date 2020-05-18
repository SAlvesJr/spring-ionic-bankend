package com.SAlvesjr.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.SAlvesjr.cursomc.domain.Categoria;
import com.SAlvesjr.cursomc.domain.dto.CategoriaDTO;
import com.SAlvesjr.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	CategoriaService categoria;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findCategoria() {
		List<Categoria> cat = categoria.findAll();
		List<CategoriaDTO> objDto = cat.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Long id) {
		Categoria obj = categoria.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insertCategoria(@Valid @RequestBody CategoriaDTO objDTO) {
		Categoria cat = categoria.fromDTO(objDTO);
		cat = categoria.insert(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> putCategoria(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Long id) {
		Categoria cat = categoria.fromDTO(objDTO);
		cat.setId(id);
		cat = categoria.update(cat);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Categoria> deleteCategoria(@PathVariable Long id) {
		categoria.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Categoria> cat = categoria.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> objDto = cat.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(objDto);
	}

}
