package org.example.spring.service;


import lombok.RequiredArgsConstructor;
import org.example.spring.database.repository.CompanyRepository;
import org.example.spring.database.repository.UserRepository;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
}
