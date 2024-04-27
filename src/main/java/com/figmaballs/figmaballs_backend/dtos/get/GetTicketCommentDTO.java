package com.figmaballs.figmaballs_backend.dtos.get;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetTicketCommentDTO {
    private Long id;
    private Long ticketId;
    private Long userId;
    private String comment;
    private Long commentDate;
}
