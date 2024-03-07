package com.figmaballs.figmaballs_backend;

import com.figmaballs.figmaballs_backend.repos.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * A fast slice test will only start jpa context.
 * <p>
 * To use other context beans use org.springframework.context.annotation.@Import annotation.
 */
@DataJpaTest
@ActiveProfiles("database")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = PostgresContextInitializer.class)
public class AbstractDatabaseTest {

    @Autowired
    protected TicketRepository projectRepository;
    @Autowired
    protected EmployeeAssocRepository employeeAssocRepository;

    @BeforeEach
    void setUp() {
        projectRepository.deleteAll();
        employeeAssocRepository.deleteAll();
    }
}