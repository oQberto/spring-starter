package org.example.spring.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class UserFilterDto {
    String firstName;
    String lastName;
    LocalDate birthDate;
}
