package org.example.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.enums.Role;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.service.CompanyService;
import org.example.spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * {@code @Controller} - это аннотация в Spring Framework, которая используется для пометки класса как компонента, который
 * обрабатывает HTTP-запросы в веб-приложении. Класс, отмеченный @Controller, становится частью механизма управления
 * контроллерами (Controller) в Spring MVC (Model-View-Controller).
 * <p>
 * Основные характеристики @Controller:
 * <p>
 * HTTP-обработка: Классы, отмеченные @Controller, могут обрабатывать HTTP-запросы от клиентов и возвращать HTTP-ответы.
 * Они могут обрабатывать разные типы запросов, такие как GET, POST, PUT, DELETE и другие, в зависимости от аннотаций методов.
 * <p>
 * Автоматическое обнаружение: Классы, помеченные @Controller, автоматически обнаруживаются Spring-контейнером
 * и регистрируются как компоненты, которые могут обрабатывать запросы.
 * <p>
 * Обработка параметров запроса: Методы внутри класса @Controller могут принимать параметры запроса (query parameters),
 * параметры пути (path parameters), заголовки (headers) и другие данные запроса, а также возвращать модели данных или представления.
 * <p></p>
 * <p>
 * {@code @RequestMapping} - это аннотация в Spring Framework, которая используется для настройки маршрутов (URL-путей) и
 * методов обработки HTTP-запросов в контроллерах. Она позволяет определить, какие URL-адреса должны быть обработаны
 * какими методами контроллера и какие параметры запроса и заголовки должны соответствовать для выполнения обработки.
 * <p>
 * Основные атрибуты @RequestMapping:
 * <p>
 * 1. value (или path): Определяет URL-путь или шаблон URL, который должен соответствовать для вызова метода контроллера.
 * Этот атрибут обязателен. Может быть строкой или массивом строк.
 * <p>
 * 2. method: Определяет HTTP-метод (GET, POST, PUT, DELETE и другие), который должен быть использован для вызова метода
 * контроллера. По умолчанию, метод не ограничен, и контроллер будет обрабатывать запросы для любых методов. Этот атрибут может быть строкой или массивом строк.
 * <p>
 * 3. params: Определяет параметры запроса, которые должны быть присутствующими и соответствовать заданным значениям,
 * чтобы метод контроллера был вызван. Это атрибут может быть строкой или массивом строк.
 * <p>
 * 4. headers: Определяет заголовки запроса, которые должны быть присутствующими и соответствовать заданным значениям,
 * чтобы метод контроллера был вызван. Это атрибут может быть строкой или массивом строк.
 * <p>
 * 5. consumes: Определяет типы контента, которые контроллер способен принимать (например, "application/json", "text/html").
 * Это атрибут может быть строкой или массивом строк.
 * <p>
 * 6. produces: Определяет типы контента, которые контроллер способен генерировать в ответ
 * (например, "application/json", "text/html"). Это атрибут может быть строкой или массивом строк.
 * <p>
 * {@code @RequestMapping} можно использовать как на уровне класса контроллера, так и на уровне отдельных методов, что
 * позволяет более гибко настраивать маршруты и параметры запросов для каждой операции обработки запросов в вашем веб-приложении.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final CompanyService companyService;

    /**
     * {@code @GetMapping} - это аннотация в Spring Framework, которая предоставляет удобный способ настройки метода контроллера
     * для обработки HTTP GET-запросов. Эта аннотация является частью аннотационного подхода в Spring MVC и позволяет
     * определить, какой URL-путь должен соответствовать методу контроллера и какие параметры запроса должны быть использованы.
     * <p>
     * Основные характеристики @GetMapping:
     * <p>
     * Замена @RequestMapping: @GetMapping является более специфичной аннотацией и может использоваться вместо более
     * общей аннотации @RequestMapping(value = "/путь", method = RequestMethod.GET) для обработки GET-запросов.
     * <p>
     * Указание URL-пути: Аннотация @GetMapping принимает один атрибут value, который определяет URL-путь, по которому
     * метод должен обрабатывать GET-запросы. Этот атрибут обязателен.
     * <p>
     * Обработка параметров запроса: Методы, отмеченные @GetMapping, могут принимать параметры запроса (query parameters),
     * которые передаются в URL. Например, @GetMapping("/hello?name=John") ожидает наличие параметра "name" в запросе и может использовать его.
     * <p>
     * Обработка пути (Path Variables): Кроме параметров запроса, @GetMapping также поддерживает обработку пути (path variables),
     * что позволяет включать переменные в URL-путь и передавать их в метод контроллера. Например, @GetMapping("/users/{id}")
     * ожидает переменную "id" в URL и может использовать её.
     * <p>
     * {@code @GetMapping} делает код контроллера более читаемым и экономит время на написание шаблонных запросов для каждого метода.
     *
     * @param model
     * @return
     */
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    /**
     * {@code @PathVariable} - это аннотация в Spring Framework, которая используется в методах контроллера для извлечения
     * значений переменных из URL-пути (path variables). Эти значения могут быть включены в URL-путь как часть пути и
     * могут быть использованы в методе контроллера для дальнейшей обработки.
     * <p>
     * Основные характеристики @PathVariable:
     * <p>
     * Извлечение переменных из URL: Аннотация @PathVariable позволяет извлекать значения переменных из URL-пути и
     * передавать их в метод контроллера для обработки.
     * <p>
     * Имя переменной: Вы указываете имя переменной, которую вы хотите извлечь из URL-пути, внутри аннотации @PathVariable.
     * Это имя должно совпадать с именем переменной в URL-пути.
     * <p>
     * Значения по умолчанию: Вы можете указать значения по умолчанию для переменных, используя атрибут defaultValue
     * в аннотации @PathVariable.
     * <p>
     * Обязательные переменные: Вы можете указать, что переменная из URL-пути обязательна, установив атрибут required
     * в значение true. Если переменная отсутствует в URL-пути, будет сгенерировано исключение.
     * <p>
     * Использование @PathVariable полезно, когда вам нужно передавать и использовать переменные из URL-пути в методах
     * контроллера, например, для получения информации о пользователе, продукте или других ресурсах, и для формирования
     * соответствующего ответа на запрос.
     * <p></p>
     * Когда ищем пользователя по url, в случае если не нашли его, надо пробросить исключение
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String findById(Model model,
                           @PathVariable("id") Long id) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("roles", Role.values());
                    model.addAttribute("companies", companyService.findAll());
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model,
                               @ModelAttribute("user") UserCreateEditDto user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());

        return "user/registration";
    }

    /**
     * {@code @PostMapping} - это аннотация в Spring Framework, которая предоставляет удобный способ настройки метода
     * контроллера для обработки HTTP POST-запросов. Эта аннотация является частью аннотационного подхода в Spring MVC
     * и используется для определения, какой URL-путь должен соответствовать методу контроллера и какие параметры
     * запроса и тело запроса должны быть использованы.
     * <p>
     * Основные характеристики @PostMapping:
     * <p>
     * Замена @RequestMapping: @PostMapping является более специфичной аннотацией и может использоваться вместо более
     * общей аннотации @RequestMapping(value = "/путь", method = RequestMethod.POST) для обработки POST-запросов.
     * <p>
     * Указание URL-пути: Аннотация @PostMapping принимает один атрибут value, который определяет URL-путь, по которому
     * метод должен обрабатывать POST-запросы. Этот атрибут обязателен.
     * <p>
     * Обработка параметров запроса: Методы, отмеченные @PostMapping, могут принимать параметры запроса
     * (query parameters), которые передаются в URL. Например, @PostMapping("/create?name=John") ожидает наличие параметра
     * "name" в запросе и может использовать его.
     * <p>
     * Обработка тела запроса: @PostMapping также позволяет методам контроллера получать данные из тела
     * HTTP-запроса. Эти данные могут быть переданы в метод контроллера как объекты, JSON или другие форматы данных.
     * <p>
     * Использование @PostMapping позволяет определить, какие методы контроллера должны обрабатывать POST-запросы, и
     * какие параметры и данные должны быть использованы для выполнения операций, таких как создание, обновление
     * или удаление ресурсов в веб-приложении.
     * <p></p>
     * В случае create мы должны возвращать статус 201.
     *
     * @param user
     * @return
     */
    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute UserCreateEditDto user,
                         RedirectAttributes redirectAttributes) {
//        if (true) {
//            redirectAttributes.addFlashAttribute("user", user);
//            return "redirect:/users/registration";
//        }
        return "redirect:/users/" + userService.create(user).getId();
    }

    /**
     * {@code @ModelAttribute} - это аннотация в Spring Framework, которая используется для связывания параметров метода с
     * атрибутами модели (Model) в веб-приложении. Эта аннотация позволяет передавать данные из запроса (как параметры
     * запроса, тело запроса или атрибуты сессии) в метод контроллера и добавлять их в модель, которая будет
     * использоваться для генерации HTML-представления.
     * <p>
     * Основные характеристики @ModelAttribute:
     * <p>
     * Использование параметров метода: Вы можете пометить параметры метода в контроллере аннотацией @ModelAttribute,
     * чтобы указать, что данные должны быть извлечены из запроса и переданы в метод.
     * <p>
     * Имя атрибута модели: Вы можете указать имя атрибута модели, в который данные должны быть добавлены с помощью
     * атрибута value аннотации @ModelAttribute. Если имя не указано, будет использовано имя параметра метода.
     * Например, @ModelAttribute("user") означает, что данные будут добавлены в атрибут модели с именем "user".
     * <p>
     * Использование объектов: @ModelAttribute позволяет передавать объекты (например, JavaBeans) из запроса в метод
     * контроллера и использовать их для выполнения операций. Например, в форме редактирования профиля пользователя
     * данные могут быть переданы объекту User, измененному и сохраненному в базе данных.
     * <p>
     * Привязка к формам: @ModelAttribute часто используется в связке с HTML-формами. Если атрибут value совпадает с
     * именем атрибута формы, то данные будут автоматически связываться и передаваться в метод контроллера.
     * <p>
     * Использование @ModelAttribute упрощает обработку данных из запроса и интеграцию их с моделью в Spring MVC,
     * что делает работу с формами и передачей данных между клиентом и сервером более удобными и гибкими.
     *
     * @param id
     * @param user
     * @return
     */
    //    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("user") UserCreateEditDto user) {
        return userService.update(id, user)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //    @DeleteMapping("/{id}")
    @PostMapping("/{id}/remove")
    public String remove(@PathVariable("id") Long id) {
        if (!userService.remove(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }
}
