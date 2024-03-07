package com.figmaballs.figmaballs_backend.dtos.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateUserGroupDTO {
    private String name;
    private String displayName;
}
