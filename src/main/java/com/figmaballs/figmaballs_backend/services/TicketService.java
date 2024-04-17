package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.repos.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public TicketEntity create(TicketEntity entity) {
        return this.repository.save(entity);
    }

    public TicketEntity update(TicketEntity newEntity) {
        TicketEntity entityToUpdate = this.readById(newEntity.getId());
        entityToUpdate.setDescription(newEntity.getDescription());
        entityToUpdate.setStatus(newEntity.getStatus());
        entityToUpdate.setFinishDate(newEntity.getFinishDate());
        entityToUpdate.setAppendIds(newEntity.getAppendIds());
        entityToUpdate.setCategoryIds(newEntity.getCategoryIds());
        return this.repository.save(entityToUpdate);
    }

    public List<TicketEntity> readAll() {
        return this.repository.findAll();
    }

    public TicketEntity readById(long id) {
        return this.repository.getOne(id);
    }
}
