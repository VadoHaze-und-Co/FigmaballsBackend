package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.CategoryEntity;
import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.repos.CategoryRepository;
import com.figmaballs.figmaballs_backend.repos.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public CategoryEntity create(CategoryEntity entity) {
        return this.repository.save(entity);
    }

    public CategoryEntity update(CategoryEntity newEntity) {
        CategoryEntity entityToUpdate = this.readById(newEntity.getId());
        entityToUpdate.setName(newEntity.getName());
        return this.repository.save(entityToUpdate);
    }

    public List<CategoryEntity> readAll() {
        return this.repository.findAll();
    }

    public CategoryEntity readById(long id) {
        return this.repository.getOne(id);
    }
}
