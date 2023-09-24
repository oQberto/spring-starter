package org.example.spring.service;


import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.Company;
import org.example.spring.database.repository.CrudRepository;
import org.example.spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;
}
