package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.dtos.create.CreateLogDTO;
import com.figmaballs.figmaballs_backend.entities.LogEntity;
import com.figmaballs.figmaballs_backend.repos.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    private final LogRepository repository;

    public LogService(LogRepository repository) {
        this.repository = repository;
        loadTestLogs();
    }

    public void loadTestLogs() {
        log("Ticket", "Create", "Max Mustermann", "bla bla");
        log("User", "Create", "Max Mustermann", "bla bla");
        log("Ticket", "Change", "Mister Man", "bla bla");
        log("User", "Change", "David Haller", "bla bla");
        log("Ticket", "Delete", "Till Staude", "bla bla");
        log("Ticket", "Create", "Max Simon", "bla bla");
        log("Ticket", "Change", "Barney Stinson", "bla bla");
        log("Ticket", "Delete", "Mattis Matthies", "bla bla");
    }

    public void log(String object, String action, String user, String message) {
        repository.save(new LogEntity(object, action, message, user));
    }

    public void log(CreateLogDTO dto) {
        log(dto.getObject(), dto.getAction(), dto.getMessage(), dto.getUser());
    }

    public List<LogEntity> logs() {
        return repository.findAll();
    }
}
