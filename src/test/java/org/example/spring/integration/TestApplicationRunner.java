package org.example.spring.integration;

import org.example.spring.database.pool.ConnectionPool;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;

/**
 *  Этот runner используется для того, что бы создавать
 *  конфигурацию для всех тестов. Для того чтобы не создавалось
 *  несколько application-ов
 * <p>
 *  Это за кешированный контекст.
 */
@TestConfiguration
public class TestApplicationRunner {

    @SpyBean(name = "connectionPool")
    private ConnectionPool connectionPool;
}
