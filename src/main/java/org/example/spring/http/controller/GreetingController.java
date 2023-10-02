package org.example.spring.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @GetMapping(value = "/hello")
    public ModelAndView hello(ModelAndView modelAndView){
        modelAndView.setViewName("greeting/hello");

        // установка атрибута
        modelAndView.addObject("user", new UserReadDto(1L, "username"));
        return modelAndView;
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

//        @RequestMapping(
//            value = "/bye",
//            method = RequestMethod.GET
//    )
    @GetMapping(value = "/bye")
    public ModelAndView bye(ModelAndView modelAndView,
                            @SessionAttribute("user") UserReadDto user) {
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }
}
