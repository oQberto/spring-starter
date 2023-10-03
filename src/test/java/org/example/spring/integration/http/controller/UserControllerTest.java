package org.example.spring.integration.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.example.spring.dto.UserCreateEditDto.Fields.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * {@code @AutoConfigureMockMvc} - это аннотация в Spring Boot, которая используется для автоматической настройки
 * объекта MockMvc. MockMvc - это класс из Spring Test, который предоставляет удобные средства для тестирования веб-приложений
 * Spring MVC без реального запуска веб-сервера. Это позволяет проводить интеграционное тестирование
 * контроллеров и обработчиков запросов без реальных HTTP-запросов и ответов.
 * <p>
 * Основные характеристики @AutoConfigureMockMvc:
 * <p>
 * Автоматическая настройка MockMvc: Аннотация @AutoConfigureMockMvc позволяет автоматически настраивать объект
 * MockMvc в контексте вашего приложения Spring Boot. Это делает объект MockMvc доступным для использования в ваших тестовых классах.
 * <p>
 * Удобство тестирования: С MockMvc вы можете выполнять HTTP-запросы к вашим контроллерам и проверять полученные
 * ответы, не запуская реальный веб-сервер. Это упрощает написание тестов и ускоряет их выполнение.
 * <p>
 * Поддержка моков и заглушек: Вы можете использовать MockMvc в сочетании с моками и заглушками для тестирования
 * контроллеров в изолированной среде.
 */
@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest extends IntegrationTestBase {
    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void findById() {
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                        .param(username, "user@gmail.com")
                        .param(firstName, "firstname")
                        .param(lastName, "lastname")
                        .param(role, "ADMIN")
                        .param(companyId, "1")
                        .param(birthDate, "2000-01-01")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }
}