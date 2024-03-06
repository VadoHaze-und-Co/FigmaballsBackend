package de.szut.lf8_project.dtos;

import de.szut.lf8_project.entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CreateTicketDTO {

    @NotBlank(message = "Description must not be blank")
    private String description;
    @NotNull(message = "The ticket needs a status, by default 0")
    private long status;
    private LocalDateTime creationDate;
    private LocalDateTime finishDate;
    private List<Long> categories;

}
