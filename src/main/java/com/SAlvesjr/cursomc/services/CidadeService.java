package com.SAlvesjr.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAlvesjr.cursomc.domain.Cidade;
import com.SAlvesjr.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public List<Cidade> findByEstado(Long estado_id) {
		return cidadeRepository.findCidades(estado_id);
	}

}
