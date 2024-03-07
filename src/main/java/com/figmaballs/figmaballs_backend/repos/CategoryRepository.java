package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
