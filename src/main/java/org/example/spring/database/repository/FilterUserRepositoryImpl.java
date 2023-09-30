package org.example.spring.database.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.User;
import org.example.spring.database.querydsl.QPredicates;
import org.example.spring.dto.UserFilterDto;

import java.util.List;

import static org.example.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilterDto userFilterDto) {
        Predicate predicate = QPredicates.builder()
                .add(userFilterDto.firstName(), user.firstName::containsIgnoreCase)
                .add(userFilterDto.lastName(), user.lastName::containsIgnoreCase)
                .add(userFilterDto.birthDate(), user.birthDate::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }
}
