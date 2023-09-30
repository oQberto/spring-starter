package org.example.spring.database.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.User;
import org.example.spring.database.entity.enums.Role;
import org.example.spring.database.querydsl.QPredicates;
import org.example.spring.dto.PersonalInfo;
import org.example.spring.dto.UserFilterDto;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.example.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {
    private static final String FIND_BY_COMPANY_AND_ROLE = """
            SELECT
            firstname,
            lastname,
            birth_date
            FROM users
            WHERE company_id = ?
                AND role = ?
            """;
    private static final String UPDATE_COMPANY_AND_ROLE = """
            UPDATE users
            SET company_id = ?,
                role = ?
            WHERE id = ?
            """;

    private final EntityManager entityManager;

    /**
     * JdbcTemplate - это класс в Spring Framework, который предоставляет удобный способ взаимодействия с базой данных
     * через JDBC (Java Database Connectivity). JdbcTemplate упрощает выполнение SQL-запросов, обработку результатов и
     * управление соединением с базой данных. Он предоставляет абстракцию над сложным и рутинным кодом JDBC, что делает
     * работу с базой данных более эффективной и безопасной.
     * <p>
     * Вот основные возможности и способы использования JdbcTemplate:<br>
     * <p>
     * 1. Инициализация JdbcTemplate:<br>
     * Для использования JdbcTemplate в Spring-приложении, вы должны создать бин этого класса в конфигурационном классе.
     * <p>
     * 2. Выполнение SQL-запросов:<br>
     * JdbcTemplate предоставляет методы для выполнения SQL-запросов, включая query, update, и другие.
     * <p>
     * 3. Обработка результатов:<br>
     * Результаты SQL-запросов могут быть обработаны с использованием RowMapper или ResultSetExtractor.
     * Вы можете создать собственные классы-мапперы для соответствия столбцам в результирующем наборе.
     * <p>
     * 4. Параметризованные запросы:<br>
     * JdbcTemplate поддерживает параметризованные запросы для безопасного выполнения SQL-запросов с аргументами.
     * Вы можете передавать параметры в запрос с использованием ? и передавать их как аргументы метода update или query.
     * <p>
     * 5. Обработка исключений:<br>
     * JdbcTemplate автоматически обрабатывает исключения JDBC и преобразует их в исключения Spring. Вы можете
     * обрабатывать исключения с помощью try-catch блоков или давать им распространиться вверх по стеку вызовов.
     * <p>
     * 6. Пакетное обновление (Batch Update):<br>
     * JdbcTemplate позволяет выполнять пакетное обновление (вставка, обновление, удаление) данных в базе данных,
     * что может быть полезным при массовой обработке данных.
     * <p>
     * 7. Управление транзакциями:<br>
     * JdbcTemplate может работать в контексте управления транзакциями Spring, что позволяет вам создавать
     * транзакционно-безопасные операции базы данных.
     */
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAllByFilter(UserFilterDto userFilterDto) {
        Predicate predicate = QPredicates.builder()
                .add(userFilterDto.firstName(), user.firstName::containsIgnoreCase)
                .add(userFilterDto.lastName(), user.lastName::containsIgnoreCase)
                .add(userFilterDto.birthDate(), user.birthDate::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(
                FIND_BY_COMPANY_AND_ROLE,
                (rs, rowNum) -> new PersonalInfo(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birth_date").toLocalDate()
                ),
                companyId, role.name());
    }

    @Override
    public void updateCompanyAndRole(List<User> users) {
        List<Object[]> args = users.stream()
                .map(user -> new Object[]{user.getCompany().getId(), user.getRole().name(), user.getId()})
                .toList();

        jdbcTemplate.batchUpdate(
                UPDATE_COMPANY_AND_ROLE,
                args);
    }
}
