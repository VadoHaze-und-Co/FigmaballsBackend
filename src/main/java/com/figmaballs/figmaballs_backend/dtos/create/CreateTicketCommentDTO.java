package com.figmaballs.figmaballs_backend.dtos.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class CreateTicketCommentDTO {
    @NotNull
    private Long ticketId;
    @NotNull
    private Long userId;
    @NotBlank
    private String comment;
    @NotNull
    private Long commentDate;
    private Boolean edited;
}
