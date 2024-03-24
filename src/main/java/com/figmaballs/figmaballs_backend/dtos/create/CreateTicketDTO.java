package com.figmaballs.figmaballs_backend.dtos.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CreateTicketDTO {

    @NotBlank(message = "Title must not be blank")
    private String title;
    @NotBlank(message = "Description must not be blank")
    private String description;
    @NotNull(message = "The ticket needs a status, by default 0")
    private long status;
    private LocalDateTime creationDate;
    private LocalDateTime finishDate;
    private List<Long> categories;

}
