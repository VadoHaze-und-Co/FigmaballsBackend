package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Long> {


}
