package com.figmaballs.figmaballs_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket-comments")
public class TicketCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private TicketEntity ticket;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private UserEntity user;
    private String comment;
    private Long commentDate;

}
