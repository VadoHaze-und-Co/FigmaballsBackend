package de.szut.lf8_project.repos;

import de.szut.lf8_project.entities.AppendEntity;
import de.szut.lf8_project.entities.SettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<SettingEntity, Long> {
}
