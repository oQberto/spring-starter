package org.example.spring.service;

import org.example.spring.database.entity.Company;
import org.example.spring.database.repository.CrudRepository;
import org.example.spring.dto.CompanyReadDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final UserService userService;
    private final CrudRepository<Integer, Company> companyRepository;

    public CompanyService(UserService userService,
                          CrudRepository<Integer, Company> companyRepository) {
        this.userService = userService;
        this.companyRepository = companyRepository;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> new CompanyReadDto(entity.getId()));
    }
}
