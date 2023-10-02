package org.example.spring.dto;

import lombok.Builder;
import lombok.Value;
import org.example.spring.database.entity.enums.Role;

import java.time.LocalDate;

@Value
@Builder
public class UserReadDto {
    Long id;
    String username;
    LocalDate birthDate;
    String firstName;
    String lastName;
    Role role;
    CompanyReadDto company;
}
