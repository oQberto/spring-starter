package org.example.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.enums.Role;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.dto.UserReadDto;
import org.example.spring.integration.IntegrationTestBase;
import org.example.spring.service.UserService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;
    private final UserService userService;


    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> result = userService.findById(USER_1);
        assertThat(result).isPresent();
        result.ifPresent(user -> assertThat(user.getUsername()).isEqualTo("ivan@gmail.com"));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gamail.com",
                LocalDate.now(),
                "FTestName",
                "LTestName",
                Role.ADMIN,
                COMPANY_1
        );
        UserReadDto actualResult = userService.create(userDto);

        assertThat(actualResult.getUsername()).isEqualTo(userDto.getUsername());
        assertThat(actualResult.getFirstName()).isEqualTo(userDto.getFirstName());
        assertThat(actualResult.getLastName()).isEqualTo(userDto.getLastName());
        assertThat(actualResult.getBirthDate()).isEqualTo(userDto.getBirthDate());
        assertThat(actualResult.getRole()).isEqualTo(userDto.getRole());
        assertThat(actualResult.getCompany().getId()).isEqualTo(userDto.getCompanyId());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gamail.com",
                LocalDate.now(),
                "FTestName",
                "LTestName",
                Role.ADMIN,
                COMPANY_1
        );
        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);
        assertThat(actualResult).isPresent();

        actualResult.ifPresent(user -> {
            assertThat(user.getUsername()).isEqualTo(userDto.getUsername());
            assertThat(user.getFirstName()).isEqualTo(userDto.getFirstName());
            assertThat(user.getLastName()).isEqualTo(userDto.getLastName());
            assertThat(user.getBirthDate()).isEqualTo(userDto.getBirthDate());
            assertThat(user.getRole()).isEqualTo(userDto.getRole());
            assertThat(user.getCompany().getId()).isEqualTo(userDto.getCompanyId());
        });
    }

    @Test
    void remove() {
        assertThat(userService.remove(USER_1)).isTrue();
        assertThat(userService.remove(-12L)).isFalse();
    }
}
