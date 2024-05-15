package com.figmaballs.figmaballs_backend.dtos.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GetTicketDTO {
    private long id;
    private String title;
    private String description;
    private long status;
    private int priority;
    private List<Long> appends;
    private List<Long> categories;
    private long creationDate;
    private long finishDate;
    private List<Long> comments;
}
