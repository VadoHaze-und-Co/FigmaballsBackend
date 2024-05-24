package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
}
