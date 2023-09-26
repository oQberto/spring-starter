package org.example.spring.service;

import org.example.spring.database.entity.Company;
import org.example.spring.database.repository.CompanyRepository;
import org.example.spring.dto.CompanyReadDto;
import org.example.spring.listener.entity.EntityEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    private static final Integer COMPANY_ID = 1;

    @Mock
    private UserService userService;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById() {
        when(companyRepository.findById(COMPANY_ID)).thenReturn(Optional.of(new Company(COMPANY_ID, null, Collections.emptyMap())));

        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actualResult.get()));

        verify(applicationEventPublisher).publishEvent(any(EntityEvent.class));
        verifyNoMoreInteractions(applicationEventPublisher, userService);
    }
}