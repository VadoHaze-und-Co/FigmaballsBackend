package de.szut.lf8_project.mappers;

import de.szut.lf8_project.dtos.create.CreateUserGroupDTO;
import de.szut.lf8_project.dtos.get.GetUserGroupDTO;
import de.szut.lf8_project.entities.UserGroupEntity;
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
