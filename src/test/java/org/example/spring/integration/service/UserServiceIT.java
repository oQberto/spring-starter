package org.example.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.integration.annotation.IT;
import org.example.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

/**
 * Аннотация @DirtiesContext - это аннотация, используемая в тестировании Spring приложений с целью пометить тестовый
 * метод или класс, чтобы сообщить Spring о том, что контекст приложения (приложение Spring) должен быть помечен как
 * "грязный" (dirty) после выполнения тестовых методов, и требуется его пересоздание. Это может быть полезно в ситуациях,
 * когда тесты внесли изменения в контекст, которые могут повлиять на другие тесты.
 * <p>
 * Вот несколько основных случаев, когда @DirtiesContext может быть полезной:
 * <p>
 * ** Изменения в ApplicationContext: Если ваш тест внес изменения в контекст приложения,
 * такие как регистрация новых бинов или изменение существующих бинов, то это может повлиять на другие тесты,
 * которые выполняются в том же контексте. Путем пометки теста @DirtiesContext вы указываете Spring на необходимость
 * пересоздания контекста после завершения этого теста.
 * <p>
 * ** Изменения в статических данных: Если ваш тест влияет на статические данные (например, файлы, базы данных), которые
 * могут влиять на другие тесты, @DirtiesContext может помочь в поддержании изоляции тестов друг от друга.
 * <p></p>
 * Такой механизм использовать не желательно(только если вручную портиться контекст), т.к. надо стараться
 * использовать как можно меньше контекстов.
 */
@IT
@RequiredArgsConstructor
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool connectionPool;

    @Test
    void test() {

    }
}
