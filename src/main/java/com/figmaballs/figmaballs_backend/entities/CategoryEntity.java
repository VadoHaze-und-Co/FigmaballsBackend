package com.figmaballs.figmaballs_backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TicketEntity ticket;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserGroupEntity userGroup;
}
