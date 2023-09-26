package org.example.spring.integration.annotation;

import org.example.spring.integration.TestApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * &#064;SpringBootTest - это аннотация из Spring Framework, которая используется в тестировании Spring-приложений для
 * создания тестового контекста приложения и выполнения интеграционных тестов. Она помогает вам интегрировать
 * Spring-контейнер в ваши тесты и позволяет вам легко взаимодействовать с вашими бинами, как если бы это было
 * в рамках реального приложения.
 * <p>
 * Вот основные характеристики @SpringBootTest:
 * <p>
 * Создание контекста приложения: @SpringBootTest создает полный контекст приложения Spring, включая все бины,
 * контроллеры, службы, репозитории и так далее, что позволяет вам выполнять интеграционное тестирование с
 * использованием реальных компонентов приложения.
 * <p>
 * Автоконфигурация: Она также автоматически включает автоконфигурацию Spring Boot, что позволяет вам легко создавать
 * тестовые сценарии для вашего приложения.
 * <p>
 * Настройка окружения: Вы можете использовать параметры @SpringBootTest для настройки вашего тестового окружения,
 * такие как указание активных профилей (properties, webEnvironment и другие).
 * <p></p>
 * &#064;ActiveProfiles - это аннотация из Spring Framework, которая используется для активации профилей в тестах.
 * Профили позволяют вам определить, какие бины и настройки должны быть активированы в зависимости от окружения или
 * цели выполнения приложения. @ActiveProfiles позволяет установить активные профили для конкретного теста, чтобы
 * настроить его окружение.
 * <p>
 * Основные характеристики @ActiveProfiles:
 * <p>
 * Активация профилей: Аннотация @ActiveProfiles позволяет явно указать, какие профили должны быть активированы для
 * выполнения теста. Это полезно, когда вам нужно выполнить тесты в различных окружениях или с разными конфигурациями.
 * <p>
 * Изоляция тестов: Активация профилей позволяет вам изолировать тесты, чтобы они не влияли друг на друга,
 * даже если они выполняются в одном и том же контексте приложения.
 * <p>
 * Комбинирование профилей: Вы можете комбинировать несколько профилей, разделяя их запятыми, чтобы настроить
 * окружение теста с учетом нескольких профилей.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@Transactional
@SpringBootTest(classes = TestApplicationRunner.class)
public @interface IT {
}
