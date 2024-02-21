package de.szut.lf8_project.repos;

import de.szut.lf8_project.entities.AppendEntity;
import de.szut.lf8_project.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
