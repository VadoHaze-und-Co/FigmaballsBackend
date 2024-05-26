package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.repos.TicketCommentRepository;
import com.figmaballs.figmaballs_backend.repos.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {

    private final TicketRepository repository;
    private final TicketCommentRepository commentRepository;

    public TicketService(TicketRepository repository, TicketCommentRepository commentRepository) {
        this.repository = repository;
        this.commentRepository = commentRepository;
        //loadTickets();
    }

    public TicketEntity create(TicketEntity entity) {
        return this.repository.save(entity);
    }

    public TicketEntity update(TicketEntity newEntity) {
        TicketEntity entityToUpdate = this.readById(newEntity.getId());
        entityToUpdate.setDescription(newEntity.getDescription());
        entityToUpdate.setStatus(newEntity.getStatus());
        entityToUpdate.setPriority(newEntity.getPriority());
        entityToUpdate.setFinishDate(newEntity.getFinishDate());
        entityToUpdate.setAppendIds(newEntity.getAppendIds());
        entityToUpdate.setCategoryIds(newEntity.getCategoryIds());
        entityToUpdate.setAssignment(newEntity.getAssignment());
        return this.repository.save(entityToUpdate);
    }

    public List<TicketEntity> readAll(boolean auto) {
        var listOfTickets = this.repository.findAll();
        if (auto) {
            listOfTickets.sort(new Comparator<>() {
                @Override
                public int compare(TicketEntity t1, TicketEntity t2) {
                    return Long.compare(t1.getCreationDate(), t2.getCreationDate());
                }
            });
        } else {
            listOfTickets.sort(new Comparator<>() {
                @Override
                public int compare(TicketEntity t1, TicketEntity t2) {
                    return Long.compare(t1.getId(), t2.getId());
                }
            });
        }
        return listOfTickets;
    }

    public TicketEntity readById(long id) {
        return this.repository.getOne(id);
    }

    public void deleteById(long id) {
        this.repository.deleteById(id);
    }
}
