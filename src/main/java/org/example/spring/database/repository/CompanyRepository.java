package org.example.spring.database.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.bpp.Auditing;
import org.example.spring.bpp.Transaction;
import org.example.spring.database.entity.Company;
import org.example.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * {@code @Slf4j} - это аннотация, которая упрощает интеграцию библиотеки SLF4J (Simple Logging Facade for Java) с классами в
 * вашем приложении. SLF4J является фасадом для различных реализаций логирования, таких, как Logback, Log4j, и других.
 * Аннотация @Slf4j генерирует код для удобной работы с логированием, без необходимости вручную создавать объекты логгеров.
 * <p>
 * Важные характеристики и использование аннотации @Slf4j:
 * <p>
 * Удобное создание логгеров: После применения аннотации @Slf4j к классу, она автоматически создает поле логгера
 * внутри класса с использованием SLF4J. Это поле можно использовать для записи логов внутри класса.
 * <p>
 * Уровни логирования: SLF4J поддерживает разные уровни логирования, такие, как DEBUG, INFO, WARN, ERROR и др.
 * Вы можете использовать созданный логгер для записи логов на нужном уровне.
 * <p>
 * Удобный синтаксис: Генерируемый логгер имеет удобный синтаксис для форматирования сообщений логов,
 * включая параметры и подстановки.
 */
@Slf4j
@Repository
@Transaction
@Auditing
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company> {

    /**
     * Если у нас имеется два несколько бинов, то spring будет искать нужный по имени
     * переменной это помогает избежать использования @Qualifier
     */
    private final ConnectionPool connectionPool;

    /**
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
    @Value("${db.pool.size}")
    private final Integer poolSize;

    @PostConstruct
    private void init() {
        log.warn("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        log.info("find by id method");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        log.info("delete method");
    }
}
