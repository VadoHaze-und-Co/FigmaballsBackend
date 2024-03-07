package com.figmaballs.figmaballs_backend.dtos.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GetUserDTO {
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String postcode;
    private String city;
    private boolean admin;
    private List<GetUserGroupDTO> userGroups;
    //private List<TicketEntity> tickets;
    //private GetSettingDTO setting;

}
