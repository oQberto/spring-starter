package org.example.spring.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * {@code @ControllerAdvice} - это аннотация в Spring Framework, которая используется для создания глобальных обработчиков
 * исключений в веб-приложениях, построенных на Spring MVC. Главная цель @ControllerAdvice - это объединить
 * обработчики исключений в одном компоненте, который может быть использован в различных контроллерах вашего приложения.
 * Это упрощает централизованное управление обработкой исключений.
 * <p>
 * Основные характеристики @ControllerAdvice:
 * <p>
 * Глобальные обработчики исключений: @ControllerAdvice позволяет создавать глобальные обработчики исключений, которые
 * будут применяться ко всем контроллерам в вашем приложении.
 * <p>
 * Централизованное управление: Вы можете объединить все обработчики исключений в одном классе и использовать его для
 * централизованного управления логикой обработки ошибок. Это упрощает поддержку и обновление обработки исключений в приложении.
 * <p>
 * Обработка исключений разных типов: @ControllerAdvice позволяет определять методы для обработки разных типов исключений,
 * таких как RuntimeException, NullPointerException, SQLException, пользовательских исключений и других.
 * <p>
 * Кастомизация ответов: Вы можете настраивать и кастомизировать ответы на ошибки, возвращаемые клиенту. Например,
 * вы можете возвращать специфичные сообщения об ошибке или устанавливать определенные HTTP-статусы.
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler /*extends ResponseEntityExceptionHandler*/ {

    /**
     * {@code @ExceptionHandler} - это аннотация в Spring Framework, которая используется в методах контроллера для обработки
     * исключений, возникающих во время выполнения запроса. Эта аннотация позволяет централизованно обрабатывать
     * исключения в приложении и возвращать клиенту корректные HTTP-ответы с информацией об ошибке.
     * <p>
     * Основные характеристики @ExceptionHandler:
     * <p>
     * Обработка исключений: @ExceptionHandler позволяет определить метод, который будет вызываться при возникновении
     * конкретного типа исключения во время обработки запроса.
     * <p>
     * Централизованное управление: Вы можете разместить методы с аннотацией @ExceptionHandler внутри вашего контроллера
     * или использовать @ControllerAdvice, чтобы создать глобальные обработчики исключений для всего приложения.
     * <p>
     * Кастомизация ответов на ошибки: Методы, помеченные @ExceptionHandler, могут кастомизировать ответы на ошибки,
     * возвращаемые клиенту. Например, вы можете установить определенный HTTP-статус, вернуть пользовательские сообщения
     * об ошибке и т. д.
     * <p>
     * Обработка разных типов исключений: Вы можете определить несколько методов @ExceptionHandler в контроллере или
     * глобальном обработчике исключений, каждый из которых обрабатывает разные типы исключений.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        log.error("Failed to return response", exception);
        return "error/error500";
    }
}