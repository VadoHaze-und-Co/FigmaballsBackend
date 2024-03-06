package de.szut.lf8_project.services;

import de.szut.lf8_project.entities.TicketEntity;
import de.szut.lf8_project.entities.UserEntity;
import de.szut.lf8_project.entities.UserSettingEntity;
import de.szut.lf8_project.repos.TicketRepository;
import de.szut.lf8_project.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity create(UserEntity entity) {
        return this.repository.save(entity);
    }

    public UserEntity update(UserEntity newEntity) {
        UserEntity entityToUpdate = this.readById(newEntity.getId());
        entityToUpdate.setFirstName(newEntity.getFirstName());
        entityToUpdate.setLastName(newEntity.getLastName());
        entityToUpdate.setEmailAddress(newEntity.getEmailAddress());
        return this.repository.save(entityToUpdate);
    }

    public List<UserEntity> readAll() {
        return this.repository.findAll();
    }

    public UserEntity readById(long id) {
        return this.repository.getOne(id);
    }
}
