package com.figmaballs.figmaballs_backend.mappers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateCategoryDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetCategoryDTO;
import com.figmaballs.figmaballs_backend.entities.CategoryEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public CategoryEntity createDtoToEntity(CreateCategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());
        return entity;
    }

    public GetCategoryDTO entityToGetDto(CategoryEntity entity) {
        return new GetCategoryDTO(entity.getName());
    }
}
