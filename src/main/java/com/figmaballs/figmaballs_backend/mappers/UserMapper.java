package com.figmaballs.figmaballs_backend.mappers;

import com.figmaballs.figmaballs_backend.dtos.get.GetUserGroupDTO;
import com.figmaballs.figmaballs_backend.entities.CategoryEntity;
import com.figmaballs.figmaballs_backend.entities.TicketCommentEntity;
import com.figmaballs.figmaballs_backend.entities.UserEntity;
import com.figmaballs.figmaballs_backend.services.CategoryService;
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
public class UserMapper extends Mapper {

    private final UserService service;
    private final CategoryService categoryService;

    public UserMapper(UserService service, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
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
        entity.setQualificationIds(idsToString(dto.getQualifikation()));
        entity.setAdmin(dto.isAdmin());

        return entity;
    }

    public GetUserDTO entityToGetDto(UserEntity entity) {
        List<Long> commentIds = new ArrayList<>();
        for (TicketCommentEntity comment : entity.getCommentedTo()) {
            commentIds.add(comment.getId());
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
                entity.getProfilePicture(),
                stringToIds(entity.getQualificationIds()),
                entity.isAdmin()
                //stringToIds(entity.getUserGroupIds()),
                //commentIds
                /*entity.getTickets().stream().toList(),*/
                /*new GetSettingDTO(
                        entity.getUserSetting().getSetting().getId(),
                        entity.getUserSetting().getSetting().getDescription()
                )*/
        );
    }
}
