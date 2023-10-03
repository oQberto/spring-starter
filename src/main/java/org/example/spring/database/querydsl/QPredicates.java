package org.example.spring.database.querydsl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

/**
 * Predicate - это интерфейс в библиотеке Querydsl, которая предоставляет возможность строить динамические
 * условия (предикаты) для запросов к базе данных в Java. Предикаты используются для фильтрации данных в
 * запросах, и они могут быть комбинированы для создания сложных условий поиска.
 * <p>
 * Основные методы интерфейса Predicate включают:
 * <p>
 * BooleanExpression and(Predicate other): Создает новый предикат, который представляет собой логическое "И"
 * (AND) между текущим предикатом и предикатом other. То есть, оба предиката должны быть истинными, чтобы результат был истинным.
 * <p>
 * BooleanExpression or(Predicate other): Создает новый предикат, который представляет собой логическое "ИЛИ"
 * (OR) между текущим предикатом и предикатом other. То есть, хотя бы один из предикатов должен быть истинным, чтобы результат был истинным.
 * <p>
 * BooleanExpression not(): Создает новый предикат, который представляет собой отрицание (NOT) текущего
 * предиката.
 * <p>
 * BooleanExpression andAll(Predicate... others): Создает новый предикат, который представляет собой логическое
 * "И" (AND) между текущим предикатом и массивом других предикатов.
 * <p>
 * BooleanExpression orAll(Predicate... others): Создает новый предикат, который представляет собой логическое
 * "ИЛИ" (OR) между текущим предикатом и массивом других предикатов.
 * <p></p>
 * ExpressionUtils - это утилитарный класс в библиотеке Querydsl, который предоставляет
 * различные статические методы для работы с выражениями (expressions) и предикатами в Querydsl. Этот класс позволяет
 * комбинировать, модифицировать и манипулировать выражениями, делая их более гибкими и мощными для создания сложных
 * запросов к базе данных.
 * <p>
 * Вот несколько из наиболее распространенных методов и операций, предоставляемых ExpressionUtils:
 * <p>
 * concat(Expression<?>... expressions): Создает выражение, которое выполняет конкатенацию строковых выражений.
 * <p>
 * list(Expression<?>... expressions): Создает выражение списка из нескольких подвыражений.
 * <p>
 * set(Expression<T> path, T value): Создает выражение для установки значения на указанный путь (атрибут сущности).
 * <p>
 * allOf(Predicate... predicates): Создает предикат, который представляет собой логическое "И" (AND) для всех
 * указанных предикатов.
 * <p>
 * anyOf(Predicate... predicates): Создает предикат, который представляет собой логическое "ИЛИ" (OR) для всех
 * указанных предикатов.
 * <p>
 * not(Predicate predicate): Создает предикат, который представляет собой отрицание (NOT) указанного предиката.
 * <p>
 * isTrue(Expression<Boolean> expression): Создает предикат для проверки, что указанное булево выражение равно true.
 * <p>
 * isFalse(Expression<Boolean> expression): Создает предикат для проверки, что указанное булево выражение равно false.
 * <p>
 * nullif(Expression<T> expression, T value): Создает выражение, которое возвращает null, если указанное выражение
 * равно заданному значению.
 * <p>
 * defaultValue(Expression<T> expression, T value): Создает выражение, которое возвращает значение указанного выражения,
 * если оно не null, иначе возвращает заданное значение.
 */
@NoArgsConstructor(access = PRIVATE)
public class QPredicates {
    private final List<Predicate> predicates = new ArrayList<>();

    public static QPredicates builder() {
        return new QPredicates();
    }

    public <T> QPredicates add(T obj, Function<T, Predicate> function) {
        if (obj != null) {
            predicates.add(function.apply(obj));
        }
        return this;
    }

    public Predicate build() {
        return ExpressionUtils.allOf(predicates);
    }
}
