package com.figmaballs.figmaballs_backend.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assignments")
public class AssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    public TicketEntity ticket;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    public UserEntity user;
}
