package org.example.spring.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.spring.database.entity.enums.Role;
import org.example.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * {@code @SessionAttributes} - это аннотация, которая используется для пометки класса контроллера и определения
 * атрибутов, которые должны сохраняться в сессии пользователя.
 * <p>
 * Обычно @SessionAttributes используется на уровне класса контроллера и принимает в качестве аргумента массив строк,
 * представляющих имена атрибутов, которые должны храниться в сессии.
 * <p>
 * Атрибуты, перечисленные в @SessionAttributes, будут автоматически сохраняться в сессии при выполнении методов
 * контроллера, а также будут доступны для чтения и изменения в других методах контроллера.
 * <p></p>
 * Обе аннотации @SessionAttributes и @SessionAttribute полезны при работе с данными, которые должны быть доступны
 * в течение всей сессии пользователя, например, информацией о текущем пользователе, состоянием корзины покупок и
 * другими данными, которые должны сохраняться между запросами клиента.
 */
@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    /**
     * Метод будет вызываться каждый раз, на каждый запрос.
     *
     * @return
     */
    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping(value = "/hello")
    public String hello(Model model,
                        @ModelAttribute("userReadDto") UserReadDto userReadDto) {

        // установка атрибута
        model.addAttribute("user", userReadDto);
        return "greeting/hello";
    }

    //        @RequestMapping(
//            value = "/hello",
//            method = RequestMethod.GET
//    )
    @GetMapping(value = "/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView, HttpServletRequest request,
                               @RequestParam("age") Integer age,
                               @RequestHeader("accept") String accept,
                               @CookieValue("JSESSIONID") String jsessionId,
                               @PathVariable("id") Integer id) {
        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }

    /**
     * Если нам надо вернуть просто статичную страницу, то вместо ModelAndView мы можем вернуть String.
     * <p>
     * {@code @SessionAttribute} - это аннотация, которая используется для извлечения значения атрибута из сессии в
     * методе контроллера.
     * <p>
     * Она применяется к параметрам метода и указывает имя атрибута, который должен быть извлечен из сессии и передан
     * в метод контроллера.
     * <p>
     * Эта аннотация полезна, когда вам нужно получить доступ к конкретному атрибуту сессии внутри метода контроллера.
     *
     * @param modelAndView
     * @param user
     * @return
     */
//        @RequestMapping(
//            value = "/bye",
//            method = RequestMethod.GET
//    )
    @GetMapping(value = "/bye")
    public String bye(ModelAndView modelAndView,
                      @SessionAttribute("user") UserReadDto user) {
        return "greeting/bye";
    }
}
