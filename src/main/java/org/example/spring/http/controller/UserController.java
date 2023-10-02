package org.example.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
//        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("{id}")
    public String findById(Model model,
                           @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.findById(id));
        return "users/user";
    }

    @PostMapping
    public String create(UserCreateEditDto user) {
//        userService.create(user);
        return "redirect:/users/" + 25;
    }

//    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("user") UserCreateEditDto user) {
//        userService.update(id, user);
        return "redirect:/users/{id}";
    }

//    @DeleteMapping("/{id}")
    @PostMapping("/{id}/remove")
    public String remove(@PathVariable("id") Long id){
//        userService.delete(id);
        return "redirect:/users";
    }
}
