package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.LogEntity;
import com.figmaballs.figmaballs_backend.repos.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogRepository repository;

    public LogService(LogRepository repository) {
        this.repository = repository;
    }

    public void loadTestLogs() {
        log("Ticket");
    }

    public void log(String object, String action, String user, String message) {
        repository.save(new LogEntity(object, action, message, user));
    }
}
