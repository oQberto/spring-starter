package org.example.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.Company;
import org.example.spring.database.entity.User;
import org.example.spring.database.repository.CompanyRepository;
import org.example.spring.dto.UserCreateEditDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        return copy(fromObject, toObject);
    }

    private User copy(UserCreateEditDto dto, User user) {
        user.setUsername(user.getUsername());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setBirthDate(user.getBirthDate());
        user.setRole(user.getRole());
        user.setCompany(getCompany(user.getCompany().getId()));

        Optional.ofNullable(dto.getPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);

        return user;
    }

    @Override
    public User map(UserCreateEditDto object) {
        return User.builder()
                .username(object.getUsername())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .birthDate(object.getBirthDate())
                .role(object.getRole())
                .company(getCompany(object.getCompanyId()))
                .build();
    }

    public Company getCompany(Integer companyId) {
        return Optional.ofNullable(companyId)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }
}
