package com.generation.projetofinalbloco02.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.projetofinalbloco02.model.Categoria;
import com.generation.projetofinalbloco02.repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Optional<Categoria> findById(Long id) {
		return repository.findById(id);
	}

	public List<Categoria> findByNome(String nome) {
		return repository.findAllByNomeContainingIgnoreCase(nome);
	}

	public Categoria insert(Categoria object) {
		return repository.save(object);
	}

	public Categoria update(Categoria object) {
		Categoria entity = repository.findById(object.getId())
				.orElseThrow(() -> new EntityNotFoundException("Não foi encontrado"));

		entity.setNome(object.getNome());
		return repository.save(entity);
	}

	public void delete(Long id) {
		repository.delete(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi encontrado")));
	}

	public boolean existsById(Long id) {
		return false;
	}
}