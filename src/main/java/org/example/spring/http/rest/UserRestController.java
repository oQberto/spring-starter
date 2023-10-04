package org.example.spring.http.rest;

import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.example.spring.dto.PageResponse;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.dto.UserFilterDto;
import org.example.spring.dto.UserReadDto;
import org.example.spring.service.UserService;
import org.example.spring.validation.group.CreateAction;
import org.example.spring.validation.group.UpdateAction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<UserReadDto> findAll(UserFilterDto filter, Pageable pageable) {
        Page<UserReadDto> page = userService.findAll(filter, pageable);

        return PageResponse.of(page);
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@Validated({Default.class, CreateAction.class}) @RequestBody UserCreateEditDto user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Long id,
                              @ModelAttribute("user") @Validated({Default.class, UpdateAction.class}) @RequestBody UserCreateEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Long id) {
        if (!userService.remove(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
