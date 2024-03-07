package com.figmaballs.figmaballs_backend.dtos.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetSettingDTO {
    private long id;
    private String description;
}
