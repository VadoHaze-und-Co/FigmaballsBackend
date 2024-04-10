package com.figmaballs.figmaballs_backend.mappers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateTicketDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetTicketDTO;
import com.figmaballs.figmaballs_backend.entities.AppendEntity;
import com.figmaballs.figmaballs_backend.entities.CategoryEntity;
import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.services.AppendService;
import com.figmaballs.figmaballs_backend.services.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class TicketMapper {

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
        entity.setStatus(0);
//        entity.setCreationDate(dto.getCreationDate());
//        entity.setFinishDate(dto.getFinishDate());
        entity.setAppends(dto.getAppends().stream().map(e -> appendService.readById(e)).toList());
        entity.setCategories(dto.getCategories().stream().map(e -> categoryService.readById(e)).toList());

        return entity;
    }

    public GetTicketDTO entityToGetDto(TicketEntity entity) {
        return new GetTicketDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getAppends().stream().map(AppendEntity::getId).toList(),
                entity.getCategories().stream().map(CategoryEntity::getId).toList(),
                entity.getCreationDate(),
                entity.getFinishDate()
        );
    }
}
