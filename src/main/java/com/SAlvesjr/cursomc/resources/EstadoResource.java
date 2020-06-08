package com.SAlvesjr.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SAlvesjr.cursomc.domain.Cidade;
import com.SAlvesjr.cursomc.domain.Estado;
import com.SAlvesjr.cursomc.domain.dto.CidadeDTO;
import com.SAlvesjr.cursomc.domain.dto.EstadoDTO;
import com.SAlvesjr.cursomc.services.CidadeService;
import com.SAlvesjr.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService;

	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = estadoService.findAll();
		List<EstadoDTO> listEstadosDTO = list.stream().map(x -> new EstadoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listEstadosDTO);
	}

	@GetMapping(value = "/{estado_id}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Long estado_id) {
		List<Cidade> list = cidadeService.findByEstado(estado_id);
		List<CidadeDTO> listDTO = list.stream().map(x -> new CidadeDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

}
