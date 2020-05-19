package com.SAlvesjr.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SAlvesjr.cursomc.domain.Produto;
import com.SAlvesjr.cursomc.domain.dto.ProdutoDTO;
import com.SAlvesjr.cursomc.resources.utils.URL;
import com.SAlvesjr.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	ProdutoService produtoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable Long id) {
		Produto obj = produtoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Produto> prod = produtoService.search(URL.decodeParam(nome), URL.decodeLongList(categorias), page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> objDto = prod.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(objDto);
	}

}
