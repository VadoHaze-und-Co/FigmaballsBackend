package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.SettingEntity;
import com.figmaballs.figmaballs_backend.entities.TicketCommentEntity;
import com.figmaballs.figmaballs_backend.repos.TicketCommentRepository;
import com.figmaballs.figmaballs_backend.repos.UserRepository;
import com.figmaballs.figmaballs_backend.entities.UserEntity;
import com.figmaballs.figmaballs_backend.repos.SettingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final SettingRepository settingRepository;
    private final TicketCommentRepository commentRepository;

    public UserService(UserRepository repository, SettingRepository settingRepository, TicketCommentRepository commentRepository) {
        this.repository = repository;
        this.settingRepository = settingRepository;
        this.commentRepository = commentRepository;
        loadUsers();
    }

    public void loadUsers() {
        String[] usernames = {"maxmustermann","mariamusterfrau","abcdefgh","reinert-zufall"};
        String[] firstName = {"Max","Maria","Admin","Reinert"};
        String[] lastNames = {"Mustermann","Musterfrau","ABCDEFGH","Zufall"};
        String[] emails = {"max.mustermann@email.com","maria.musterfrau@email.com","admin@email.com","reinert-zufall@email.com"};
        String[] address = {"Irgendwo Str. 1","Irgendwo Str. 1","Firmaweg 99","Zufallsstraße 123"};
        String[] postcodes = {"12345","12345","01254","98765"};
        String[] cities = {"Musterstadt","Musterstadt","Firmenstadt","Zufallsdorf"};
        String[] groupIds = {"2","2","5","1"};
        boolean[] isAdmins = {false,false,true,false};
        for (int i = 0; i < usernames.length; i++) {
            UserEntity user = new UserEntity();
            user.setUserName(usernames[i]);
            user.setFirstName(firstName[i]);
            user.setLastName(lastNames[i]);
            user.setEmailAddress(emails[i]);
            user.setAddress(address[i]);
            user.setPostcode(postcodes[i]);
            user.setCity(cities[i]);
            user.setUserGroupIds(groupIds[i]);
            user.setAdmin(isAdmins[i]);
            user.setCommentedTo(new ArrayList<>());
            if (i == 0 || i == 3) {
                if (i == 0) {
                    List<TicketCommentEntity> comments = new ArrayList<>();
                    comments.add(this.commentRepository.getOne(2L));
                    comments.add(this.commentRepository.getOne(4L));
                    user.setCommentedTo(comments);
                }
                else {
                    List<TicketCommentEntity> comments = new ArrayList<>();
                    comments.add(this.commentRepository.getOne(1L));
                    comments.add(this.commentRepository.getOne(3L));
                    user.setCommentedTo(comments);
                }
            }
            this.repository.save(user);
        }

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
