package com.figmaballs.figmaballs_backend.mappers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateTicketDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetTicketDTO;
import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.services.AppendService;
import com.figmaballs.figmaballs_backend.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketMapper extends Mapper {

    public AppendService appendService;
    public CategoryService categoryService;

    public TicketMapper(AppendService appendService, CategoryService categoryService) {
        this.appendService = appendService;
        this.categoryService = categoryService;
    }

    public TicketEntity ticketCreateDtoToEntity(CreateTicketDTO dto) {
        TicketEntity entity = new TicketEntity();
        entity.setId(0);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setPriority(dto.getPriority());
        entity.setCreationDate(dto.getCreationDate());
        entity.setFinishDate(dto.getFinishDate());
        entity.setAppendIds(idsToString(dto.getAppends()));
        entity.setCategoryIds(idsToString(dto.getCategories()));
        entity.setAssignment(dto.getAssignment());

        return entity;
    }

    public GetTicketDTO entityToGetDto(TicketEntity entity) {

        return new GetTicketDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getPriority(),
                entity.getCreationDate(),
                entity.getFinishDate(),
                stringToIds(entity.getAppendIds()),
                stringToIds(entity.getCategoryIds()),
                entity.getAssignment()
        );
    }
}
