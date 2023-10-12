package org.example.spring.service;


import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.example.spring.database.querydsl.QPredicates;
import org.example.spring.database.repository.UserRepository;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.dto.UserFilterDto;
import org.example.spring.dto.UserReadDto;
import org.example.spring.mapper.UserCreateEditMapper;
import org.example.spring.mapper.UserReadMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.example.spring.database.entity.QUser.user;

/**
 * Аннотация @Transactional в Spring позволяет настраивать различные параметры для управления транзакциями.
 * Вот список наиболее часто используемых параметров аннотации @Transactional:
 * <p>
 * propagation (Пропагация): Параметр propagation определяет, как должна вести себя транзакция, если метод вызывается из
 * другого метода, уже выполняющегося в транзакции. Возможные значения включают:
 * <p><p>
 * Propagation.REQUIRED (по умолчанию): Метод будет выполняться в рамках существующей транзакции, если она существует.
 * В противном случае будет создана новая транзакция.<p>
 * Propagation.REQUIRES_NEW: Всегда создается новая транзакция для выполнения метода, независимо от существующей транзакции.<p>
 * Propagation.SUPPORTS: Метод будет выполняться без транзакции, если она существует, и в рамках существующей транзакции,
 * если она есть. Не создает новую транзакцию.<p>
 * Propagation.NOT_SUPPORTED: Метод будет выполняться без транзакции, даже если она существует, и в рамках существующей
 * транзакции, если она есть. Не создает новую транзакцию.<p>
 * Propagation.MANDATORY: Метод требует существования активной транзакции. Если транзакции нет, будет выброшено исключение.<p>
 * Propagation.NEVER: Метод требует отсутствия активной транзакции. Если транзакция существует, будет выброшено исключение.<p><p>
 * isolation (Изоляция): Параметр isolation определяет уровень изоляции транзакции. Уровень изоляции определяет, какие
 * изменения в данных видны другим транзакциям во время выполнения текущей транзакции. Возможные значения включают:
 * <p><p>
 * Isolation.DEFAULT: Использует уровень изоляции, установленный по умолчанию в базе данных.<p>
 * Isolation.READ_UNCOMMITTED: Другие транзакции видят нефиксированные изменения.<p>
 * Isolation.READ_COMMITTED: Другие транзакции видят только зафиксированные изменения.<p>
 * Isolation.REPEATABLE_READ: Другие транзакции видят только зафиксированные изменения и не могут вставлять новые строки.<p>
 * Isolation.SERIALIZABLE: Другие транзакции не могут одновременно изменять данные.<p><p>
 * timeout (Тайм-аут): Параметр timeout определяет максимальное время (в секундах), в течение которого транзакция может
 * быть активной. Если транзакция продолжается дольше, она будет автоматически откатываться.
 * <p><p>
 * readOnly (Только для чтения): Параметр readOnly указывает, что метод выполняет только операции чтения и не выполняет
 * изменения в базе данных. Это позволяет Spring оптимизировать транзакцию для увеличения производительности.
 * <p><p>
 * rollbackFor (Откат при исключении): Параметр rollbackFor позволяет указать классы исключений, при которых транзакция
 * должна быть автоматически откачена (откат). Например, @Transactional(rollbackFor = {RuntimeException.class, MyCustomException.class})
 * указывает, что транзакция будет откатываться при возникновении исключения типа RuntimeException или MyCustomException.
 * <p><p>
 * noRollbackFor (Без отката при исключении): Параметр noRollbackFor позволяет указать классы исключений,
 * при которых транзакция не должна откатываться. Например, @Transactional(noRollbackFor = {MyCustomException.class})
 * указывает, что транзакция не будет откатываться при возникновении исключения типа MyCustomException.
 * <p><p>
 * Эти параметры позволяют тонко настраивать поведение транзакции в зависимости от требований приложения.
 * Вы можете выбирать сочетания параметров, которые наиболее подходят для вашей бизнес-логики и целей транзакций.
 * <p></p>
 * Больше о @Transactional: {@link org.example.spring.service.CompanyService}
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public Page<UserReadDto> findAll(UserFilterDto filter, Pageable pageable) {
        Predicate predicate = QPredicates.builder()
                .add(filter.getFirstName(), user.firstName::containsIgnoreCase)
                .add(filter.getLastName(), user.lastName::containsIgnoreCase)
                .add(filter.getBirthDate(), user.birthDate::before)
                .build();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(user -> userCreateEditMapper.map(userDto, user))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean remove(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrive user: " + username));
    }
}
