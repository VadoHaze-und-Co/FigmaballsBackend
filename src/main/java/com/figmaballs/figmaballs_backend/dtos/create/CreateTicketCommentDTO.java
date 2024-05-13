package com.figmaballs.figmaballs_backend.dtos.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
public class CreateTicketCommentDTO {
    @NotBlank
    private Long ticketId;
    @NotBlank
    private Long userId;
    @NotBlank
    private String comment;
    @NotBlank
    private Long commentDate;
    private Boolean edited;
}
