package org.example.spring.dto;

import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.example.spring.database.entity.enums.Role;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    String username;

    /**
     * Аннотация используется для конвертации дат в тестах,
     * но при возможности ее лучше не использовать, только в конкретных случаях.
     * Лучше использовать вариант через properties в application.yaml
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    String firstName;
    String lastName;
    Role role;
    Integer companyId;
}
