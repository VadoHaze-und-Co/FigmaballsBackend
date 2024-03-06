package de.szut.lf8_project.dtos.get;

import de.szut.lf8_project.entities.TicketEntity;
import de.szut.lf8_project.entities.UserGroupEntity;
import de.szut.lf8_project.entities.UserSettingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GetUserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String postcode;
    private String city;
    private boolean admin;
    private List<GetUserGroupDTO> userGroups;
    /*private List<TicketEntity> tickets;
    private UserSettingEntity setting;*/

}
