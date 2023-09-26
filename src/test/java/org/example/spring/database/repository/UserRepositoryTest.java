package org.example.spring.database.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.User;
import org.example.spring.integration.annotation.IT;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void findAllBy() {
        List<User> users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
    }
}