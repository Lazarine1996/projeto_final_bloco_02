package com.generation.projeto_final_bloco_02.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.projeto_final_bloco_02.models.Product;
import com.generation.projeto_final_bloco_02.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public List<Product> findByName(String name) {
        return repository.findAllByNameContainingIgnoreCase(name);
    }

    public Product insert(Product obj) {
        return repository.save(obj);
    }

    public Product update(Product obj) {
        Product entity = repository.findById(obj.getId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrada"));

        entity.setName(obj.getName());
        entity.setPrice(obj.getPrice());
        entity.setDescription(obj.getDescription());
        entity.setStockQuantity(obj.getStockQuantity());
        entity.setCategory(categoryService.findById(obj.getCategory().getId()).get());

        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrada")));
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}