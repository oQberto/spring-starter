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

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    /**
     * Метод будет вызываться каждый раз, на каждый запрос.
     * @return
     */
    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping(value = "/hello")
    public String hello(Model model,
                        @ModelAttribute("userReadDto") UserReadDto userReadDto){

        // установка атрибута
        model.addAttribute("user", new UserReadDto(1L, "username"));
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
