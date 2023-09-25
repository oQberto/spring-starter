package org.example.spring.config;

import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.database.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;


@Configuration
public class ApplicationConfiguration {

    /**
     * {@code @Bean} - это аннотация в Spring Framework, которая используется для определения методов, которые создают
     * и настраивают бины в Spring контейнере. Методы, помеченные @Bean, предоставляют конфигурацию для создания
     * и настройки компонентов в приложении. Эти методы обычно определены в классах, помеченных
     * аннотацией @Configuration.
     * <p>
     * Основные характеристики и использование аннотации @Bean:
     * <p>
     * Создание бинов: Методы, помеченные @Bean, создают и настраивают бины. Это позволяет полностью
     * контролировать процесс создания объектов в Spring контейнере.
     * <p>
     * Именование бинов: Методы, помеченные @Bean, возвращают объекты, которые становятся бинами Spring.
     * Вы можете использовать атрибут name аннотации для явного задания имени бина. Если имя не указано,
     * оно будет автоматически сгенерировано на основе имени метода.
     * <p>
     * Зависимости и конфигурация: Методы @Bean могут иметь параметры, которые внедряются из контекста Spring.
     * Это позволяет настраивать бины с учетом их зависимостей.
     * <p>
     * Профили: Вы можете использовать аннотацию @Profile вместе с @Bean, чтобы определить,
     * в каких профилях приложения данный бин должен быть создан.
     * <p></p>
     * {@code @Scope} - это аннотация в Spring Framework, которая используется для определения области (scope) бина, т.е.
     * времени его жизни и доступности в контейнере Spring. С помощью @Scope вы можете указать, как долго бин должен
     * существовать и каким образом он должен быть доступен в рамках приложения.
     * <p>
     * Spring предоставляет несколько стандартных областей (scope), таких, как singleton, prototype, request, session и
     * другие. Вы также можете создавать свои собственные пользовательские области.
     * <p>
     * Основные характеристики и использование аннотации @Scope:
     * <p>
     * Стандартные области: Spring предоставляет несколько стандартных областей, которые определяют, как долго бин будет
     * существовать. Например, singleton означает, что бин будет создан только один раз и будет использоваться во всем
     * приложении, а prototype означает, что будет создан новый экземпляр бина каждый раз, когда он запрашивается.
     * <p>
     * Свои собственные области: Вы можете создавать собственные пользовательские области, реализуя интерфейс
     * org.springframework.beans.factory.config. Scope и регистрируя их в контейнере Spring. Это позволяет настраивать
     * более сложные сценарии управления временем жизни бинов.
     * <p>
     * Использование аннотации @Scope: Для применения @Scope к бину, просто пометьте класс или метод, который определяет
     * бин, этой аннотацией и укажите имя или значение стандартной, или пользовательской области.
     */
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
