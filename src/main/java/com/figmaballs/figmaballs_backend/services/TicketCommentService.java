package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.TicketCommentEntity;
import com.figmaballs.figmaballs_backend.repos.TicketCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketCommentService {
    private final TicketCommentRepository repository;

    public TicketCommentService(TicketCommentRepository repository) {
        this.repository = repository;
        loadComments();
    }

    private void loadComments() {

    }


    public TicketCommentEntity create(TicketCommentEntity entity) {
        return this.repository.save(entity);
    }

    public TicketCommentEntity update(TicketCommentEntity newEntity) {
        TicketCommentEntity entityToUpdate = this.readById(newEntity.getId());
        entityToUpdate.setTicket(newEntity.getTicket());
        entityToUpdate.setUser(newEntity.getUser());
        entityToUpdate.setComment(newEntity.getComment());
        entityToUpdate.setCommentDate(newEntity.getCommentDate());
        return this.repository.save(entityToUpdate);
    }

    public List<TicketCommentEntity> readAll() {
        return this.repository.findAll();
    }

    public TicketCommentEntity readById(long id) {
        return this.repository.getOne(id);
    }

    public List<TicketCommentEntity> readByUserId(long userId) {
        List<TicketCommentEntity> result = this.repository.findAll();
        result.removeIf(t -> t.getUser().getId() != userId);
        return result;
    }

    public List<TicketCommentEntity> readByTicketId(long ticketId) {
        List<TicketCommentEntity> result = this.repository.findAll();
        result.removeIf(t -> t.getTicket().getId() != ticketId);
        return result;
    }
}
