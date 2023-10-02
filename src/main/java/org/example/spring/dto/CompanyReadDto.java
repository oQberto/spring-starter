package org.example.spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CompanyReadDto {
    Integer id;
    String name;
}
