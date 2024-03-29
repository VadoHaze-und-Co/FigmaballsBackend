package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.SettingEntity;
import com.figmaballs.figmaballs_backend.repos.UserRepository;
import com.figmaballs.figmaballs_backend.entities.UserEntity;
import com.figmaballs.figmaballs_backend.repos.SettingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final SettingRepository settingRepository;

    public UserService(UserRepository repository, SettingRepository settingRepository) {
        this.repository = repository;
        this.settingRepository = settingRepository;
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

    public void delete(UserEntity entity) {
        this.repository.delete(entity);
    }

    public List<UserEntity> readAllAsAdmins() {
        var admins = this.repository.findAll();
        admins.removeIf(userEntity -> !userEntity.isAdmin());
        return admins;
    }

    public SettingEntity getSetting(long id) {
        return this.settingRepository.getOne(id);
    }
}
