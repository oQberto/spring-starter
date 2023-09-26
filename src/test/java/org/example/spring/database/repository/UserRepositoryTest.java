package org.example.spring.database.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.User;
import org.example.spring.database.entity.enums.Role;
import org.example.spring.integration.annotation.IT;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void findAllBy() {
        List<User> users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
    }

    @Test
    void checkUpdateRole() {
        User user = userRepository.getReferenceById(1L);
        assertSame(Role.ADMIN, user.getRole());

        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        User sameUser = userRepository.getReferenceById(1L);
        assertSame(Role.USER, sameUser.getRole());
    }
}