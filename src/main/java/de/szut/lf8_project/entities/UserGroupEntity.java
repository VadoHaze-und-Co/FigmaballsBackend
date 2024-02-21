package de.szut.lf8_project.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_groups")
public class UserGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String displayName;

    @ManyToMany(mappedBy = "userGroups", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserEntity> users;

    @OneToMany(mappedBy = "userGroup", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CategoryEntity> categories;

    @OneToMany(mappedBy = "userGroup", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PermissionEntity> permissions;
}
