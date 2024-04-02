package com.figmaballs.figmaballs_backend.dtos.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCategoryDTO {

    @NotBlank
    private String name;
}
