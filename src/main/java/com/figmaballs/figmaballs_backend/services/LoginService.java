package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.config.PassEncryptionConfig;
import com.figmaballs.figmaballs_backend.entities.LoginEntity;
import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.repos.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    private final LoginRepository repository;
    private final PassEncryptionConfig passEncryptionConfig = new PassEncryptionConfig();

    public LoginService(LoginRepository repository) {
        this.repository = repository;
    }

    public LoginEntity readById(long id) {
        return this.repository.getOne(id);
    }

    public List<LoginEntity> readAll() {
        return repository.findAll();
    }

    public LoginEntity create(LoginEntity entity) {
        return this.repository.save(entity);
    }

    public LoginEntity update(LoginEntity newEntity) {
        LoginEntity entityToUpdate = this.readById(newEntity.getId());
        entityToUpdate.setUser(newEntity.getUser());
        entityToUpdate.setLastLogin(newEntity.getLastLogin());
        entityToUpdate.setPassword(newEntity.getPassword());
        entityToUpdate.setSecuredPassword(newEntity.getSecuredPassword());
        entityToUpdate.setSaltValue(newEntity.getSaltValue());

        return this.repository.save(entityToUpdate);
    }

}
