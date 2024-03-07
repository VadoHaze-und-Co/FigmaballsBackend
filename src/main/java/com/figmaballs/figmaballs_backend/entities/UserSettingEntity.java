package com.figmaballs.figmaballs_backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_settings")
public class UserSettingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private SettingEntity setting;
    @OneToOne
    private UserEntity user;
}
