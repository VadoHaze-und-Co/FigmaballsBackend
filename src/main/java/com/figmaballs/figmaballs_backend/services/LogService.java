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
    }

    public void log(String object, String action, String user, String message) {
        repository.save(new LogEntity(object, action, message, user));
    }

    public void log(CreateLogDTO dto) {
        log(dto.getObject(), dto.getAction(), dto.getUser(), dto.getMessage());
    }

    public List<LogEntity> logs() {
        return repository.findAll();
    }
}
