package de.szut.lf8_project.dtos.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetUserGroupDTO {
    private long id;
    private String name;
    private String displayName;
}
