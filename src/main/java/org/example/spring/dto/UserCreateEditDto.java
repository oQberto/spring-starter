package org.example.spring.dto;

import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.example.spring.database.entity.enums.Role;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    String username;
    LocalDate birthDate;
    String firstName;
    String lastName;
    Role role;
    Integer companyId;
}
