package org.example.spring;

import org.example.spring.config.DatabaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@code @SpringBootApplication} - это мощная аннотация в Spring Boot, которая объединяет несколько других аннотаций
 * для упрощения конфигурации и запуска приложений на платформе Spring. Она является аннотацией мета-конфигурации,
 * которая объединяет следующие аннотации:
 * <p>
 * {@code @Configuration:} Обозначает класс как конфигурационный и позволяет определить бины в приложении.
 * <p>
 * {@code @EnableAutoConfiguration:} Автоматически настраивает Spring-приложение на основе классов в пути classpath и
 * настройках, что позволяет создавать приложения с минимальными усилиями по конфигурации.
 * <p>
 * {@code @ComponentScan:} Сканирует и регистрирует компоненты (классы, помеченные @Component, @Service, @Repository,
 * и др.) в контексте приложения.
 * <p>
 * {@code @SpringBootApplication} используется как аннотация верхнего уровня для вашего главного класса
 * (класса, содержащего метод main)
 * <p></p>
 *{@code @ConfigurationPropertiesScan} - это аннотация в Spring Boot, введенная в версии Spring Boot 2.2, которая используется
 *  для сканирования пакетов и обнаружения классов, помеченных аннотацией @ConfigurationProperties. Это удобное средство,
 *  которое позволяет автоматически обнаруживать классы, предназначенные для привязки внешних настроек к Java-объектам (POJOs).
 * <p>
 * Основные характеристики и использование @ConfigurationPropertiesScan:
 * <p>
 * Сканирование пакетов: @ConfigurationPropertiesScan сканирует указанные пакеты в поисках классов, помеченных аннотацией
 * {@code @ConfigurationProperties,} и регистрирует их в контексте Spring. Это позволяет Spring Boot автоматически преобразовывать
 * внешние конфигурации в Java-объекты.
 * <p>
 * Простое подключение настроек: При использовании @ConfigurationProperties вы можете создать POJO для хранения
 * конфигурационных параметров вашего приложения и автоматически привязать значения из application.properties или
 * application.yml файлов к полям этого класса.
 * <p>
 * Явная конфигурация пакетов: Вы можете явно указать, какие пакеты должны быть отсканированы с помощью @ConfigurationPropertiesScan, либо дать ему сканировать пакет, в котором находится сама аннотация.
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println(context.getBeanDefinitionCount());
        System.out.println(context.getBean(DatabaseProperties.class));
    }
}
