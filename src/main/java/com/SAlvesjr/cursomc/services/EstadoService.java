package com.SAlvesjr.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAlvesjr.cursomc.domain.Estado;
import com.SAlvesjr.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> findAll() {
		return estadoRepository.findAllByOrderByNome();
	}
}
