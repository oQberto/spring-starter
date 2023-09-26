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
 * <p></p>
 * {@code @Value} - это аннотация в Spring Framework, которая используется для инъекции значений свойств в поля классов.
 * С помощью @Value вы можете получить доступ к значениям свойств из файла конфигурации (например,
 * application.properties или application.yml) и использовать их в вашем коде.
 * <p>
 * Основные характеристики и использование аннотации @Value:
 * <p>
 * Инъекция значений: @Value позволяет вам инъектировать значения свойств в поля классов, независимо от их типа
 * (примитивы, строки, объекты и т. д.).
 * <p>
 * Работа с внешними конфигурациями: Вы можете использовать @Value для получения значений свойств из файлов
 * конфигурации, таких как application.properties или application.yml. Это удобно для настройки приложения из внешних источников.
 * <p>
 * Значения по умолчанию: Если значение свойства не найдено, вы можете предоставить значение по умолчанию с помощью
 * {@code @Value,} что позволяет вашему приложению продолжать работу даже при отсутствии конкретных настроек.
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
