package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
