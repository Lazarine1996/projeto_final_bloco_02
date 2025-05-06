package com.generation.projetofinalbloco02.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.projetofinalbloco02.model.Produto;
import com.generation.projetofinalbloco02.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private CategoriaService categoriaService;

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public Optional<Produto> findById(Long id) {
		return repository.findById(id);
	}

	public List<Produto> findByName(String nome) {
		return repository.findAllByNomeContainingIgnoreCase(nome);
	}

	public Produto insert(Produto objetic) {
		return repository.save(objetic);
	}

	public Produto update(Produto objetic) {
		Produto entity = repository.findById(objetic.getId())
				.orElseThrow(() -> new EntityNotFoundException("Não foi encontrado o produto"));

		entity.setNome(objetic.getNome());
		entity.setValor(objetic.getValor());
		entity.setDescricao(objetic.getDescricao());
		entity.setQuantidadeEstoque(objetic.getQuantidadeEstoque());
		entity.setCategoria(categoriaService.findById(objetic.getCategoria().getId()).get());

		return repository.save(entity);
	}

	public void delete(Long id) {
		repository.delete(
				repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi encontrado o produto")));
	}

	public Boolean existsById(Long id) {
		return repository.existsById(id);
	}
}