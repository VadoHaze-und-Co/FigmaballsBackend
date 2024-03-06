package de.szut.lf8_project.dtos;

import de.szut.lf8_project.entities.SettingEntity;
import de.szut.lf8_project.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToOne;

public class CreateUserSettingDTO {
    private long id;
    private long settingId;
    private String value;
}
