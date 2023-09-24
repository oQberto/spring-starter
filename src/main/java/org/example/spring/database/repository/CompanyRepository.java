package org.example.spring.database.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.spring.bpp.Auditing;
import org.example.spring.bpp.Transaction;
import org.example.spring.database.entity.Company;
import org.example.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transaction
@Auditing
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool connectionPool; //если у нас имеется два несколько бинов, то spring будет искать нужный по имени переменной это помогает избежать использования @Qualifier

    @Value("${db.pool.size}")
    private final Integer poolSize;

    @PostConstruct
    private void init() {
        System.out.println("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("find by id method");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method");
    }
}
