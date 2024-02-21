package de.szut.lf8_project.repos;

import de.szut.lf8_project.entities.UserSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingRepository extends JpaRepository<UserSettingEntity, Long> {
}
