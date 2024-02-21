package de.szut.lf8_project.repos;

import de.szut.lf8_project.entities.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserGroupEntity, Long> {
}
