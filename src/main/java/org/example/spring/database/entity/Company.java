package org.example.spring.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "company")
public class Company implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Так мы создаем таблицу company_locales. Но делаем это в виде map, т.к
     * она очень сильно связана с сущностью Company.
     * В качестве ключа выступает поле lang, в качестве значения - поле description,
     * что указано в соответствующих аннотациях.
     * <p></p>
     * Аннотация @ElementCollection является частью Java Persistence API (JPA) и используется в Java EE и Java SE для
     * работы с объектами, представляющими коллекции (например, списки, множества) в контексте персистентности данных.
     * {@code @ElementCollection} используется для маппинга элементов коллекции в базу данных как отдельных записей, а не как
     * отдельных сущностей.
     * <p>
     * Основные характеристики и использование аннотации @ElementCollection:
     * <p>
     * Маппинг коллекций: @ElementCollection используется для маппинга элементов коллекции в базу данных. Она позволяет
     * сохранять каждый элемент коллекции в отдельной записи таблицы базы данных, связанной с основной сущностью.
     * <p>
     * Тип элементов: Вы должны указать тип элементов коллекции с помощью атрибута targetClass. Это позволяет JPA знать,
     * какой тип данных хранится в коллекции.
     * <p>
     * Таблица элементов: С помощью аннотации @CollectionTable (которую можно использовать вместе с @ElementCollection)
     * вы определяете настройки для таблицы, которая будет содержать элементы коллекции.
     * <p>
     * Join Column: С помощью атрибута joinColumns аннотации @CollectionTable вы определяете столбцы, которые связывают
     * таблицу элементов коллекции с таблицей основной сущности.
     * <p></p>
     * {@code @CollectionTable} - это аннотация в Java Persistence API (JPA), которая используется для настройки маппинга
     * коллекций (например, списков или множеств) в базе данных. Эта аннотация позволяет определить дополнительные
     * настройки для таблицы, которая будет создана для хранения элементов коллекции, когда используется аннотация
     * {@code @ElementCollection.}
     * <p>
     * Основные характеристики и использование аннотации @CollectionTable:
     * <p>
     * Имя таблицы: С помощью атрибута name вы указываете имя таблицы, которая будет создана для хранения элементов
     * коллекции. Это имя будет использовано при создании схемы базы данных.
     * <p>
     * Схема (Schema) и каталог (Catalog): С атрибутами schema и catalog вы можете указать схему и каталог, в которых
     * должна быть создана таблица. Эти атрибуты могут быть полезными в многодоменных базах данных.
     * <p>
     * Уникальные ограничения (Unique Constraints): С помощью атрибута uniqueConstraints можно указать уникальные
     * ограничения для столбцов таблицы, чтобы гарантировать уникальность значений.
     */
    @Builder.Default
    @ElementCollection
    @CollectionTable(
            name = "company_locales",
            joinColumns = @JoinColumn(name = "company_id")
    )
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String, String> locales = new HashMap<>();
}
