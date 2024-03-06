package de.szut.lf8_project.dtos.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @NotBlank(message = "address must not be blank")
    private String address;
    @NotBlank(message = "postcode must not be blank")
    private String postcode;
    @NotBlank(message = "city must not be blank")
    private String city;
    private boolean admin;
    @NotEmpty(message = "al least one existed user group should be")
    private List<Long> userGroups;
}
