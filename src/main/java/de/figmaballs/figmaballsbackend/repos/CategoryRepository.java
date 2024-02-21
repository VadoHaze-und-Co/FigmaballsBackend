package de.szut.lf8_project.repos;

import de.szut.lf8_project.entities.CategoryEntity;
import de.szut.lf8_project.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
