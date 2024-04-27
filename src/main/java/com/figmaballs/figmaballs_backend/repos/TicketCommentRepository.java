package com.figmaballs.figmaballs_backend.repos;

import com.figmaballs.figmaballs_backend.entities.TicketCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCommentRepository extends JpaRepository<TicketCommentEntity, Long> {
}
