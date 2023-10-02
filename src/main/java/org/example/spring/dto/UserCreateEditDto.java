package org.example.spring.dto;

import lombok.Value;
import org.example.spring.database.entity.enums.Role;

import java.time.LocalDate;

@Value
public class UserCreateEditDto {
    String username;
    LocalDate birthDate;
    String firstName;
    String lastName;
    Role role;
    Integer companyId;
}
