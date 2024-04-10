package com.figmaballs.figmaballs_backend.mappers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateAppendDTO;
import com.figmaballs.figmaballs_backend.dtos.create.CreateCategoryDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetAppendDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetCategoryDTO;
import com.figmaballs.figmaballs_backend.entities.AppendEntity;
import com.figmaballs.figmaballs_backend.entities.CategoryEntity;
import org.springframework.stereotype.Service;

@Service
public class AppendMapper {

    public AppendEntity createDtoToEntity(CreateAppendDTO dto) {
        AppendEntity entity = new AppendEntity();
        String name = dto.getFileName();
        for (int i = name.length() - 1; i >= 0; i--) {
            if (name.charAt(i) == '.') {
                entity.setFileName(name.substring(0, i - 1));
                entity.setFileType(name.substring(i + 1));
            }
        }
        entity.setContent(dto.getContent());
        return entity;
    }

    public GetAppendDTO entityToGetDto(AppendEntity entity) {
        return new GetAppendDTO(entity.getId(), entity.getFileName(), entity.getFileType(), entity.getContent());
    }
}
