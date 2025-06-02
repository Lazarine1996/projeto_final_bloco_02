package com.generation.projeto_final_bloco_02.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.projeto_final_bloco_02.models.Category;
import com.generation.projeto_final_bloco_02.repositories.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    public List<Category> findByDepartment(String department) {
        return repository.findAllByDepartmentContainingIgnoreCase(department);
    }

    public Category insert(Category obj) {
        return repository.save(obj);
    }

    public Category update(Category obj) {
        Category entity = repository.findById(obj.getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        entity.setDepartment(obj.getDepartment());
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada")));
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}