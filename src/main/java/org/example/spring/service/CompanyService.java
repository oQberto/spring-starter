package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.repository.CompanyRepository;
import org.example.spring.dto.CompanyReadDto;
import org.example.spring.listener.entity.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.example.spring.listener.entity.AccessType.READ;

/**
 * Аннотация @Service является одной из аннотаций в Spring Framework и используется для пометки класса как сервиса
 * (службы). Классы, помеченные @Service, обычно представляют собой службы или компоненты, которые выполняют
 * бизнес-логику в приложении. Эта аннотация является частью механизма инверсии управления (IoC) Spring и помогает
 * Spring управлять и управлять бинами в контейнере.
 * <p>
 * Вот основные характеристики и использование аннотации @Service:
 * <p>
 * Объявление бинов: Когда вы помечаете класс аннотацией @Service, Spring обнаруживает этот класс и создает бин этого
 * класса в контейнере. Это позволяет вам внедрять (инжектировать) экземпляры этого класса в другие компоненты вашего
 * приложения.
 * <p>
 * Управление жизненным циклом: Spring управляет жизненным циклом бинов, помеченных @Service. Он создает экземпляры
 * сервисов, управляет их инициализацией и уничтожением, а также обеспечивает инъекцию зависимостей (DI) в эти сервисы.
 * <p>
 * Инъекция зависимостей: Классы, помеченные @Service, могут использовать другие аннотации Spring, такие, как @Autowired,
 * для инъекции зависимостей в свои поля, что упрощает управление зависимостями между компонентами.
 * <p></p>
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
 * <p></p>
 * Если мы хотим, чтобы транзакция открывалась только для определенного метода, мы ставим аннотацию @Transactional
 * над соответствующим методом. Если поставить @Transactional и над методом и над классом, то аннотация над методом
 * будет иметь приоритет.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final UserService userService;
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    applicationEventPublisher.publishEvent(new EntityEvent(entity, READ));
                    return new CompanyReadDto(entity.getId());
                });
    }
}
