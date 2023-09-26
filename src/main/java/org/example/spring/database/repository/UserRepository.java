package org.example.spring.database.repository;

import org.example.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * {@code @Repository} - это аннотация в Spring Framework, которая используется для пометки класса как репозитория (хранилища)
 * данных. Репозитории являются частью архитектуры доступа к данным (Data Access Layer) и предоставляют способ
 * взаимодействия с базой данных или другими источниками данных. Классы, помеченные @Repository, обычно используются
 * для выполнения операций чтения и записи данных в базе данных.
 * <p>
 * Основные характеристики и использование аннотации @Repository:
 * <p>
 * Обнаружение бинов: Классы, помеченные аннотацией @Repository, автоматически обнаруживаются Spring и регистрируются
 * как Spring бины в контексте приложения. Это позволяет использовать их для взаимодействия с данными, выполняя
 * операции базы данных.
 * <p>
 * Исключение обработки: Классы, помеченные @Repository, обычно используются для доступа к данным и могут автоматически
 * обрабатывать исключения, связанные с доступом к данным, и преобразовывать их в Spring исключения
 * (например, DataAccessException), что упрощает обработку ошибок доступа к данным.
 * <p>
 * Интеграция с Spring Data: @Repository часто используется в сочетании с Spring Data, что позволяет создавать
 * репозитории с помощью интерфейсов и автоматически создавать реализацию этих репозиториев Spring Data.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Тут используется HQL
     * @param firstname
     * @param lastname
     * @return
     */
    @Query(value = """
            select u
            from User u
            where u.firstName like %:firstname% and
                u.lastName like %:lastname%
            """)
    List<User> findAllBy(String firstname, String lastname);

    /**
     * Тут используется native SQL
     * @param username
     * @return
     */
    @Query(value = """
            select u.*
            from users u
            where u.username = :username
            """,
            nativeQuery = true)
    List<User> findAllByUsername(String username);
}
