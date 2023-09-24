package org.example.spring.config;

import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.database.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;


@Configuration
public class ApplicationConfiguration {

        @Bean
        @Scope(SCOPE_SINGLETON)
        public ConnectionPool connectionPool1() {
                return new ConnectionPool("test-name", 20);
        }

        @Bean
        @Scope(SCOPE_SINGLETON)
        public ConnectionPool connectionPool2() {
                return new ConnectionPool("test-name", 20);
        }

        @Bean
        public UserRepository userRepository1(ConnectionPool connectionPool) {
                return new UserRepository(connectionPool);
        }
}
