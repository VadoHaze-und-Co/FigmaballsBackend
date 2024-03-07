package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
