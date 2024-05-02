package com.figmaballs.figmaballs_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(length = 999)
    private String description;

    private long status;

    private int priority;

    private long creationDate;

    private long finishDate;

    private String appendIds;

    private String categoryIds;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssignmentEntity> assignments = new ArrayList<>();

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TicketCommentEntity> comments = new ArrayList<>();

//    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private CategoryEntity creator;
}
