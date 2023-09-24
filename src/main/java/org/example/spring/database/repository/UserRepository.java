package org.example.spring.database.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @Qualifier("connectionPool2")
    private final ConnectionPool connectionPool;
}
