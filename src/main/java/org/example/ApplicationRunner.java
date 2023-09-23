package org.example;

import org.example.spring.config.ApplicationConfiguration;
import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            ConnectionPool connection = context.getBean("connectionPool", ConnectionPool.class);
            System.out.println(connection);

            CompanyService companyService = context.getBean("companyService", CompanyService.class);
            System.out.println(companyService.findById(1));
        }
    }
}
