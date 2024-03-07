package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.UserSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingRepository extends JpaRepository<UserSettingEntity, Long> {
}
