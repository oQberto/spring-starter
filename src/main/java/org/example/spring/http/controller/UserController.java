package org.example.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    /**
     * Когда ищем пользователя по url, в случае если не нашли его, надо пробросить исключение
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public String findById(Model model,
                           @PathVariable("id") Long id) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    return "users/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * В случае create мы должны возвращать статус 201.
     *
     * @param user
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(UserCreateEditDto user) {
        return "redirect:/users/" + userService.create(user).getId();
    }

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
