package de.szut.lf8_project.repos;

import de.szut.lf8_project.entities.StatusEntity;
import de.szut.lf8_project.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
