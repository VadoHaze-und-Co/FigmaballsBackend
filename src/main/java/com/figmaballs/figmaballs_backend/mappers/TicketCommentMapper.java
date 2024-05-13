package com.figmaballs.figmaballs_backend.mappers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateTicketCommentDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetTicketCommentDTO;
import com.figmaballs.figmaballs_backend.entities.TicketCommentEntity;
import com.figmaballs.figmaballs_backend.services.TicketCommentService;
import com.figmaballs.figmaballs_backend.services.TicketService;
import com.figmaballs.figmaballs_backend.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class TicketCommentMapper {
    private TicketCommentService service;
    private TicketService ticketService;
    private UserService userService;

    public TicketCommentMapper(TicketCommentService service, TicketService ticketService, UserService userService) {
        this.service = service;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    public TicketCommentEntity createDtoToEntity(CreateTicketCommentDTO dto) {
        TicketCommentEntity entity = new TicketCommentEntity();
        entity.setId(0L);
        entity.setTicket(this.ticketService.readById(dto.getTicketId()));
        entity.setUser(this.userService.readById(dto.getUserId()));
        entity.setComment(dto.getComment());
        entity.setCommentDate(dto.getCommentDate());
        entity.setEdited(dto.getEdited());
        return entity;
    }

    public GetTicketCommentDTO entityToGetDTO(TicketCommentEntity entity) {
        return new GetTicketCommentDTO(
                entity.getId(),
                entity.getTicket().getId(),
                entity.getUser().getId(),
                entity.getComment(),
                entity.getCommentDate(),
                entity.getEdited()
        );
    }
}
