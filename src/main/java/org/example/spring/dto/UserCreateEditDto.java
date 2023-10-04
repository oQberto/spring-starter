package org.example.spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.example.spring.database.entity.enums.Role;
import org.example.spring.validation.UserInfo;
import org.example.spring.validation.group.CreateAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = CreateAction.class)
public class UserCreateEditDto {
    @Email
    String username;

    /**
     * Аннотация используется для конвертации дат в тестах,
     * но при возможности ее лучше не использовать, только в конкретных случаях.
     * Лучше использовать вариант через properties в application.yaml
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    @Size(min = 3, max = 64)
    String firstName;

    String lastName;

    @NotNull
    Role role;

    @NotNull
    Integer companyId;
}
