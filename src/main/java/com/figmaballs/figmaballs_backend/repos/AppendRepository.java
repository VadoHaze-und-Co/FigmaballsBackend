package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.AppendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppendRepository extends JpaRepository<AppendEntity, Long> {
}
