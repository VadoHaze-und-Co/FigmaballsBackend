package com.figmaballs.figmaballs_backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String Address;

    private String postcode;

    private String city;

    @ManyToMany
    private Set<UserGroupEntity> userGroups;

    private boolean admin;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TicketEntity> tickets;

    @OneToOne(mappedBy = "user")
    private UserSettingEntity userSetting;
}
