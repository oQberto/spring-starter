package org.example.spring.database.repository;

import org.example.spring.bpp.InjectBean;
import org.example.spring.database.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool connectionPool;
}
