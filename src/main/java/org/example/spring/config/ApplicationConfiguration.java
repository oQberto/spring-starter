package org.example.spring.config;

import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.database.repository.CrudRepository;
import org.example.spring.database.repository.UserRepository;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;
import static org.springframework.context.annotation.FilterType.*;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "org.example.spring",
        useDefaultFilters = false,
        includeFilters = {
                @Filter(type = ANNOTATION, value = Component.class),
                @Filter(type = ASSIGNABLE_TYPE, value = CrudRepository.class),
                @Filter(type = REGEX, pattern = "com\\..+Repository"),
        })
public class ApplicationConfiguration {

        @Bean
        @Scope(SCOPE_SINGLETON)
        public ConnectionPool connectionPool1() {
                return new ConnectionPool("test-name", 20);
        }

        @Bean
        public UserRepository userRepository1(ConnectionPool connectionPool) {
                return new UserRepository(connectionPool);
        }
}
