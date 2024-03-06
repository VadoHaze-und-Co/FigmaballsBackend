package de.szut.lf8_project.services;

import de.szut.lf8_project.entities.TicketEntity;
import de.szut.lf8_project.repos.TicketRepository;
import io.netty.handler.codec.http2.Http2Connection;
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
        entityToUpdate.setAppends(newEntity.getAppends());
        entityToUpdate.setCategories(newEntity.getCategories());
        return this.repository.save(entityToUpdate);
    }

    public List<TicketEntity> readAll() {
        return this.repository.findAll();
    }

    public TicketEntity readById(long id) {
        return this.repository.getOne(id);
    }
}
