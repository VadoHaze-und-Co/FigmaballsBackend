package de.szut.lf8_project.mappers;

import de.szut.lf8_project.dtos.create.CreateTicketDTO;
import de.szut.lf8_project.dtos.get.GetTicketDTO;
import de.szut.lf8_project.entities.TicketEntity;
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
