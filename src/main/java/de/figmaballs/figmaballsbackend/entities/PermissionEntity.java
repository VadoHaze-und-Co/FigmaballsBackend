package de.szut.lf8_project.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    private String name;

    private int value;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserGroupEntity userGroup;
}
