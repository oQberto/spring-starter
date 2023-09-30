package org.example.spring.database.repository;

import org.example.spring.database.entity.User;
import org.example.spring.database.entity.enums.Role;
import org.example.spring.dto.PersonalInfo;
import org.example.spring.dto.UserFilterDto;

import java.util.List;

/**
 * Custom Repository Implementation (Настройка собственной реализации репозитория) - это механизм, предоставляемый
 * Spring Data JPA, который позволяет вам создать собственные методы в репозиториях, а также их собственную реализацию.
 * Это позволяет добавить пользовательскую логику в репозитории, которая не может быть достигнута с использованием
 * стандартных методов Spring Data JPA.
 * <p>
 * Чтобы создать собственный метод и его реализацию в репозитории, выполните следующие шаги:
 * <p>
 * 1. Определите собственный метод в интерфейсе репозитория:<br>
 * В интерфейсе репозитория определите собственный метод, который соответствует вашим требованиям. Этот метод должен
 * следовать соглашениям именования Spring Data JPA.
 * <p>
 * 2. Создайте реализацию собственного метода:
 * Создайте класс с реализацией вашего собственного метода. Этот класс должен иметь следующие характеристики:
 * <p>
 * 2.1 Имя должно соответствовать имени репозитория с суффиксом "Impl" (например, CustomBookRepositoryImpl).<br>
 * 2.2 Реализовать интерфейс CustomBookRepository.<br>
 * 2.3 Переопределить собственный метод.
 * <p>
 * 3. Интегрируйте собственную реализацию с репозиторием:<br>
 * В вашем репозитории добавьте аннотацию @RepositoryImplementation и укажите, какой класс содержит вашу
 * собственную реализацию. Это сообщит Spring Data JPA, что собственная реализация репозитория должна быть
 * использована для выполнения собственного метода.
 * <p>
 * Теперь у вас есть собственный метод findBooksByCustomCriteria в вашем репозитории, а его реализация находится в
 * классе CustomBookRepositoryImpl. Вы можете использовать этот метод для выполнения пользовательских запросов к базе
 * данных с использованием EntityManager или других средств доступа к данным.
 * <p>
 * Помимо этого, вы также можете использовать аннотацию @Query для определения пользовательских запросов в интерфейсе
 * репозитория без создания собственной реализации.
 * <p></p>
 * Кроме сущностей тут можно работать и с проекциями.
 */
public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilterDto userFilterDto);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);
}
