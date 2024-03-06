package de.szut.lf8_project.mappers;

import de.szut.lf8_project.dtos.*;
import de.szut.lf8_project.entities.TicketEntity;
import de.szut.lf8_project.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserEntity userCreateDtoToEntity(CreateUserDTO dto) {
        var entity = new UserEntity();
        entity.setId(0);
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmailAddress(dto.getEmailAddress());
        entity.setUserGroups(null);
        entity.setUserSetting(null);
        entity.setTickets(null);

        return entity;
    }

    public GetUserDTO entityToGetDto(UserEntity entity) {
        return new GetUserDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmailAddress()
                /*entity.getUserGroups().stream().toList(),
                entity.getTickets().stream().toList(),
                entity.getUserSetting()*/
        );
    }
}
