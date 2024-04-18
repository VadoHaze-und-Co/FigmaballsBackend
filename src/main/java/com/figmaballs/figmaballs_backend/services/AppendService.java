package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.AppendEntity;
import com.figmaballs.figmaballs_backend.entities.CategoryEntity;
import com.figmaballs.figmaballs_backend.repos.AppendRepository;
import com.figmaballs.figmaballs_backend.repos.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppendService {

    private final AppendRepository repository;
    private final String CONTENT_ONE = "Joe Mama use Figmaballs :3";

    public AppendService(AppendRepository repository) {
        this.repository = repository;
        loadAppends();
    }

    private void loadAppends() {
        AppendEntity entity = new AppendEntity();
        entity.setFileName("asas");
        entity.setFileType("txt");
        entity.setContent(CONTENT_ONE);
        this.repository.save(entity);
    }

    public AppendEntity create(AppendEntity entity) {
        return this.repository.save(entity);
    }

    public AppendEntity update(AppendEntity newEntity) {
        AppendEntity entityToUpdate = this.readById(newEntity.getId());
        entityToUpdate.setFileName(newEntity.getFileName());
        entityToUpdate.setFileType(newEntity.getFileType());
        entityToUpdate.setContent(newEntity.getContent());
        return this.repository.save(entityToUpdate);
    }

    public List<AppendEntity> readAll() {
        return this.repository.findAll();
    }

    public AppendEntity readById(long id) {
        return this.repository.getOne(id);
    }
}
