package com.figmaballs.figmaballs_backend.mappers;

import com.figmaballs.figmaballs_backend.dtos.get.GetUserGroupDTO;
import com.figmaballs.figmaballs_backend.entities.UserEntity;
import com.figmaballs.figmaballs_backend.services.UserGroupService;
import com.figmaballs.figmaballs_backend.services.UserService;
import com.figmaballs.figmaballs_backend.dtos.create.CreateUserDTO;
import com.figmaballs.figmaballs_backend.dtos.get.GetUserDTO;
import com.figmaballs.figmaballs_backend.entities.UserGroupEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserMapper {

    private final UserService service;
    private final UserGroupService userGroupService;

    public UserMapper(UserService service, UserGroupService userGroupService) {
        this.service = service;
        this.userGroupService = userGroupService;
    }

    public UserEntity userCreateDtoToEntity(CreateUserDTO dto) {
        var entity = new UserEntity();
        entity.setId(0);
        entity.setUserName(dto.getUserName());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmailAddress(dto.getEmailAddress());
        entity.setAddress(dto.getAddress());
        entity.setPostcode(dto.getPostcode());
        entity.setCity(dto.getCity());
        Set<UserGroupEntity> groupEntities = new HashSet<>();
        for (var groupId : dto.getUserGroups()) {
            var groupEntity = this.userGroupService.readById(groupId);
            groupEntities.add(groupEntity);
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
                entity.getUserName(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmailAddress(),
                entity.getAddress(),
                entity.getPostcode(),
                entity.getCity(),
                entity.isAdmin(),
                groups
                /*entity.getTickets().stream().toList(),*/
                /*new GetSettingDTO(
                        entity.getUserSetting().getSetting().getId(),
                        entity.getUserSetting().getSetting().getDescription()
                )*/
        );
    }
}
