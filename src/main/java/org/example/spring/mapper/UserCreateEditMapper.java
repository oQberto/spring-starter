package org.example.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.Company;
import org.example.spring.database.entity.User;
import org.example.spring.database.repository.CompanyRepository;
import org.example.spring.dto.UserCreateEditDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User>{
    private final CompanyRepository companyRepository;

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        toObject.setUsername(fromObject.getUsername());
        toObject.setFirstName(fromObject.getFirstName());
        toObject.setLastName(fromObject.getLastName());
        toObject.setBirthDate(fromObject.getBirthDate());
        toObject.setRole(fromObject.getRole());
        toObject.setCompany(getCompany(toObject.getCompany().getId()));
        return toObject;
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
