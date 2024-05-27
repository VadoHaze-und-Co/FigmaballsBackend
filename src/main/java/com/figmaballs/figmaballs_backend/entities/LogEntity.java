package com.figmaballs.figmaballs_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "logs")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;

    public LogEntity(String object, String action, String message, String user) {
        this.action = action;
        this.object = object;
        this.message = message;
        this.username = user;
        this.time = LocalDateTime.now();
    }

    private String action;

    private String object;

    private String message;

    private String username;
}
