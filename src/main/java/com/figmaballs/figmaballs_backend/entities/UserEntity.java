package com.figmaballs.figmaballs_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String address;

    private String postcode;

    private String city;

    @Column(columnDefinition = "varchar(10485760)")
    private String profilePicture;

    private String qualificationIds;

    private boolean admin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssignmentEntity> assignedTo = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TicketCommentEntity> commentedTo = new ArrayList<>();
}
