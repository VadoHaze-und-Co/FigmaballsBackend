package de.szut.lf8_project.entities;

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

    private String firstName;

    private String lastName;

    private String emailAddress;

    @ManyToMany
    private Set<UserGroupEntity> userGroups;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TicketEntity> tickets;

    @OneToOne(mappedBy = "user")
    private UserSettingEntity userSetting;
}
