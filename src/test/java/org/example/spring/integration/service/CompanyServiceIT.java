package org.example.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.config.DatabaseProperties;
import org.example.spring.dto.CompanyReadDto;
import org.example.spring.integration.annotation.IT;
import org.example.spring.service.CompanyService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class CompanyServiceIT {
    private static final Integer COMPANY_ID = 1;
    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {

        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actualResult.get()));
    }

}
