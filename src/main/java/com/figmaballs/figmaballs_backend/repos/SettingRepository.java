package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.SettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<SettingEntity, Long> {
}
