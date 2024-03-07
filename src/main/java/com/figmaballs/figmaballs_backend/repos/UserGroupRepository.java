package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Long> {
}
