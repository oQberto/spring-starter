package org.example.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.User;
import org.example.spring.dto.CompanyReadDto;
import org.example.spring.dto.UserReadDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {
    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto map(User object) {
        CompanyReadDto company = Optional.ofNullable(object.getCompany())
                .map(companyReadMapper::map)
                .orElse(null);

        return UserReadDto.builder()
                .id(object.getId())
                .username(object.getUsername())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .birthDate(object.getBirthDate())
                .role(object.getRole())
                .company(company)
                .build();
    }
}
