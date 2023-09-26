package org.example.spring.integration.database.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.Company;
import org.example.spring.integration.annotation.IT;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@code @Transactional} - это аннотация в Spring Framework, которая используется для управления транзакциями в приложениях,
 * работающих с базами данных или другими ресурсами. Она позволяет определить, какие методы должны выполняться в рамках
 * транзакции, и обеспечивает управление началом, фиксацией и откатом транзакций.
 * <p>
 * Основные характеристики и использование аннотации @Transactional:
 * <p>
 * Аннотация метода: @Transactional обычно применяется к методам, которые должны выполняться в рамках транзакции.
 * Это означает, что все операции, выполняемые внутри этого метода, будут либо успешно зафиксированы, либо откачены в
 * случае возникновения исключения.
 * <p>
 * Начало транзакции: Когда метод, помеченный @Transactional, вызывается, Spring автоматически начинает новую транзакцию.
 * Транзакция остается активной до тех пор, пока метод завершается успешно.
 * <p>
 * Фиксация (Commit) и откат (Rollback): Если метод завершается успешно, транзакция автоматически фиксируется, и все
 * изменения в базе данных сохраняются. В случае возникновения исключения, транзакция автоматически откатывается, и все
 * изменения в базе данных сбрасываются.
 * <p>
 * Уровень изоляции и другие параметры: С помощью аннотации @Transactional вы можете настроить различные параметры
 * транзакции, такие как уровень изоляции, тайм-аут, только для чтения и другие.
 * <p>
 * Пропагация транзакции: Вы также можете настроить, как будет обрабатываться транзакция, если метод вызывается из
 * другого метода, уже выполняющегося в транзакции (например, методы могут присоединяться к существующей транзакции
 * или создавать новую).
 *<p></p>
 * По умолчанию стоит аннотация @Rollback, для того, чтобы тесты не мешали друг другу. Но при желании
 * можно установить аннотацию @Commit, если у нас реализован свой механизм очистки, после выполнения методов.
 */
@IT
@RequiredArgsConstructor
@Transactional
class CompanyRepositoryTest {

    private final EntityManager entityManager;

    @Test
    void findById() {
        Company actualResult = entityManager.find(Company.class, 1);

        assertNotNull(actualResult);
        assertThat(actualResult.getLocales()).hasSize(2);
    }
}