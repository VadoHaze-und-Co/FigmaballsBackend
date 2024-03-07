package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
