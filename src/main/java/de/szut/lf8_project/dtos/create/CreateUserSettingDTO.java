package de.szut.lf8_project.dtos.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateUserSettingDTO {
    private long id;
    private long settingId;
    private String value;
}
