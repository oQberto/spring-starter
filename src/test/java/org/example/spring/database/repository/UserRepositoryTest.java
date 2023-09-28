package org.example.spring.database.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.User;
import org.example.spring.database.entity.enums.Role;
import org.example.spring.integration.annotation.IT;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void checkPageable() {
        var pageRequest = PageRequest.of(0, 2, Sort.by("id"));
        var slice = userRepository.findAllBy(pageRequest);
        slice.forEach(user -> System.out.println(user.getCompany().getName()));

        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkSortUsingPageable() {
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));

        Page<User> result = userRepository.findAllBy(pageable);
        assertThat(result).hasSize(2);
    }

    @Test
    void checkSort() {
        Sort.TypedSort<User> sort = Sort.sort(User.class);
        Sort finalSort = sort.by(User::getFirstName)
                .and(sort.by(User::getLastName))
                .descending();

        List<User> users = userRepository.findFirst3ByBirthDate(LocalDate.of(1984, 3, 14), finalSort);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkSortDefault() {
        List<User> users = userRepository.findFirst3ByBirthDate(
                LocalDate.of(1984, 3, 14),
                Sort.by("id").descending()
        );
        assertThat(users).hasSize(2);
    }

    @Test
    void checkFirstTop() {
        Optional<User> user = userRepository.findFirstByOrderByIdDesc();
        assertTrue(user.isPresent());
        user.ifPresent(user1 -> assertEquals(5L, user1.getId()));
    }

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