package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.TicketCommentEntity;
import com.figmaballs.figmaballs_backend.repos.TicketCommentRepository;
import com.figmaballs.figmaballs_backend.repos.TicketRepository;
import com.figmaballs.figmaballs_backend.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketCommentService {
    private final TicketCommentRepository repository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketCommentService(TicketCommentRepository repository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.repository = repository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        //loadComments();
    }

    private void loadComments() {
        String[] comments = {
                "Hallo, ich habe ein Problem mit mein PC, können Sie mir helfen?",
                "Hi, nö haha ciao",
                "Hallo, bitte fix mein PC und ich gebe dir ein Döner",
                "Hallönchen, alles klar alles fürs Döner :p"
        };
        long ticketId = 1;
        long[] userIds = {4L,1L,4L,1L};
        long[] commentDates = {1714637240274L,1714637380274L,1714637120274L,1714637420274L};
        for (int i = 0; i < comments.length; i++) {
            TicketCommentEntity commentEntity = new TicketCommentEntity();
            commentEntity.setId((long)i);
            commentEntity.setTicket(this.ticketRepository.getOne(ticketId));
            commentEntity.setUser(this.userRepository.getOne(userIds[i]));
            commentEntity.setComment(comments[i]);
            commentEntity.setCommentDate(commentDates[i]);
            this.repository.save(commentEntity);
        }
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

    public void deleteById(long id) {
        this.repository.deleteById(id);
    }
}
