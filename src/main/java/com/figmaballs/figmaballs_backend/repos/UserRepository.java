package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
