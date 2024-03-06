package de.szut.lf8_project.mappers;

import de.szut.lf8_project.dtos.create.CreateUserDTO;
import de.szut.lf8_project.dtos.get.GetUserDTO;
import de.szut.lf8_project.dtos.get.GetUserGroupDTO;
import de.szut.lf8_project.entities.UserEntity;
import de.szut.lf8_project.entities.UserGroupEntity;
import de.szut.lf8_project.services.UserGroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserMapper {

    private final UserGroupService userGroupService;

    public UserMapper(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    public UserEntity userCreateDtoToEntity(CreateUserDTO dto) {
        var entity = new UserEntity();
        entity.setId(0);
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmailAddress(dto.getEmailAddress());
        entity.setAddress(dto.getAddress());
        entity.setPostcode(dto.getPostcode());
        entity.setCity(dto.getCity());
        Set<UserGroupEntity> groupEntities = new HashSet<>();
        for (var groupId : dto.getUserGroups()) {
            UserGroupEntity groupEntity = this.userGroupService.readById(groupId);
            if (groupEntity != null) {
                groupEntities.add(groupEntity);
            } else {
              return null;
            }
        }
        entity.setUserGroups(groupEntities);
        entity.setUserSetting(null);
        entity.setTickets(null);
        entity.setAdmin(dto.isAdmin());

        return entity;
    }

    public GetUserDTO entityToGetDto(UserEntity entity) {
        List<GetUserGroupDTO> groups = new ArrayList<>();
        for (UserGroupEntity userGroupEntity : entity.getUserGroups()) {
            GetUserGroupDTO userGroupDTO = userGroupService.readByIdDTO(userGroupEntity.getId());
            groups.add(userGroupDTO);
        }
        return new GetUserDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmailAddress(),
                entity.getAddress(),
                entity.getPostcode(),
                entity.getCity(),
                entity.isAdmin(),
                groups
                /*entity.getTickets().stream().toList(),
                entity.getUserSetting()*/
        );
    }
}
