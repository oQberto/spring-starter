package org.example.spring.database.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.User;
import org.example.spring.dto.UserFilterDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilterDto userFilterDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);

        Root<User> user = criteria.from(User.class);
        criteria.select(user);
        List<Predicate> predicates = new ArrayList<>();

        if (userFilterDto.firstName() != null) {
            predicates.add(criteriaBuilder.like(user.get("firstname"), userFilterDto.firstName()));
        }
        if (userFilterDto.lastName() != null) {
            predicates.add(criteriaBuilder.like(user.get("lastname"), userFilterDto.lastName()));
        }
        if (userFilterDto.birthDate() != null) {
            predicates.add(criteriaBuilder.lessThan(user.get("birthdate"), userFilterDto.birthDate()));
        }
        criteria.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteria).getResultList();
    }
}
