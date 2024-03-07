package com.figmaballs.figmaballs_backend.mappers;

import com.figmaballs.figmaballs_backend.dtos.create.CreateUserGroupDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetUserGroupDTO;
import com.figmaballs.figmaballs_backend.entities.UserGroupEntity;
import org.springframework.stereotype.Service;

@Service
public class UserGroupMapper {

    public UserGroupEntity userGroupCreateDtoToEntity(CreateUserGroupDTO dto) {
        var entity = new UserGroupEntity();
        entity.setId(0);
        entity.setName(dto.getName());
        entity.setDisplayName(dto.getDisplayName());
        return entity;
    }

    public GetUserGroupDTO entityToGetDto(UserGroupEntity entity) {

        return new GetUserGroupDTO(
                entity.getId(),
                entity.getName(),
                entity.getDisplayName()
        );
    }
}
