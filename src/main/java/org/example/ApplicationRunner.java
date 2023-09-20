package org.example;

import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.database.repository.CompanyRepository;
import org.example.spring.database.repository.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml")) {
            ConnectionPool connection = context.getBean("connectionPool", ConnectionPool.class);
            System.out.println(connection);

            CrudRepository companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
