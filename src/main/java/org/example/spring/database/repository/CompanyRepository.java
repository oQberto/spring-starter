package org.example.spring.database.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.pool.ConnectionPool;

@RequiredArgsConstructor
public class CompanyRepository {
    private final ConnectionPool connectionPool;
}
