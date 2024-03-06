package de.szut.lf8_project.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CreateUserDTO {
    @NotBlank(message = "first name must not be blank")
    private String firstName;
    @NotBlank(message = "last name must not be blank")
    private String lastName;
    @NotBlank(message = "email must not be blank")
    private String emailAddress;
    private List<Long> userGroups;
}
