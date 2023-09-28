package org.example.spring.dto;

import java.time.LocalDate;

public record UserFilterDto(String firstName,
                            String lastName,
                            LocalDate birthDate) {
}
