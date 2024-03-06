package de.szut.lf8_project.services;

import de.szut.lf8_project.dtos.get.GetUserGroupDTO;
import de.szut.lf8_project.entities.UserEntity;
import de.szut.lf8_project.entities.UserGroupEntity;
import de.szut.lf8_project.mappers.UserGroupMapper;
import de.szut.lf8_project.repos.UserGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupService {

    private final UserGroupRepository repository;
    private final UserGroupMapper mapper;

    public UserGroupService(UserGroupRepository repository, UserGroupMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserGroupEntity create(UserGroupEntity entity) {
        return this.repository.save(entity);
    }

    public UserGroupEntity update(UserGroupEntity newEntity) {
        UserGroupEntity entityToUpdate = this.readById(newEntity.getId());
        entityToUpdate.setName(newEntity.getName());
        entityToUpdate.setDisplayName(newEntity.getDisplayName());
        return this.repository.save(entityToUpdate);
    }

    public List<UserGroupEntity> readAll() {
        return this.repository.findAll();
    }

    public UserGroupEntity readById(long id) {
        return this.repository.getOne(id);
    }

    public void delete(UserGroupEntity entity) {
        this.repository.delete(entity);
    }

    public GetUserGroupDTO readByIdDTO(long id) {
        UserGroupEntity entity = this.repository.getOne(id);
        return this.mapper.entityToGetDto(entity);
    }
}
