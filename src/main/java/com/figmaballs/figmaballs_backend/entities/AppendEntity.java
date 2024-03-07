package com.figmaballs.figmaballs_backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "appends")
public class AppendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String fileType;

    private String content;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TicketEntity ticket;
}
