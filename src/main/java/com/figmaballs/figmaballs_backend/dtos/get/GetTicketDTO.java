package com.figmaballs.figmaballs_backend.dtos.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class GetTicketDTO {
    private long id;
    private String title;
    private String description;
    private long status;
    private LocalDateTime creationDate;
    private LocalDateTime finishDate;
}
