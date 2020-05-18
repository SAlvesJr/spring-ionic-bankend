package com.SAlvesjr.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.SAlvesjr.cursomc.domain.Categoria;
import com.SAlvesjr.cursomc.repositories.CategoriaRepository;
import com.SAlvesjr.cursomc.services.exception.DataIntegrityException;
import com.SAlvesjr.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public Categoria findById(Long id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria cat) {
		cat.setId(null);
		return categoriaRepository.save(cat);
	}

	public Categoria update(Categoria cat) {
		findById(cat.getId());
		return categoriaRepository.save(cat);
	}

	public void delete(Long id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível  excluir categoria que possui produtos");
		}
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

}
