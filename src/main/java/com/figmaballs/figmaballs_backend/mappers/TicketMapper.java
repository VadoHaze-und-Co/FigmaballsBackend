package com.figmaballs.figmaballs_backend.mappers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateTicketDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetTicketDTO;
import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import org.springframework.stereotype.Service;

@Service
public class TicketMapper {

    public TicketEntity ticketCreateDtoToEntity(CreateTicketDTO dto) {
        var entity = new TicketEntity();
        entity.setId(0);
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setCreationDate(dto.getCreationDate());
        entity.setFinishDate(dto.getFinishDate());
        entity.setAppends(null);
        entity.setCategories(null);

        return entity;
    }

    public GetTicketDTO entityToGetDto(TicketEntity entity) {
        return new GetTicketDTO(
                entity.getId(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreationDate(),
                entity.getFinishDate()
        );
    }
}