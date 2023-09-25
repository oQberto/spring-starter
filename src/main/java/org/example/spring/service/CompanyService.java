package org.example.spring.service;

import org.example.spring.database.entity.Company;
import org.example.spring.database.repository.CrudRepository;
import org.example.spring.dto.CompanyReadDto;
import org.example.spring.listener.entity.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

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
 */
@Service
public class CompanyService {

    private final UserService userService;
    private final CrudRepository<Integer, Company> companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CompanyService(UserService userService,
                          CrudRepository<Integer, Company> companyRepository,
                          ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.companyRepository = companyRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    applicationEventPublisher.publishEvent(new EntityEvent(entity, READ));
                    return new CompanyReadDto(entity.getId());
                });
    }
}
