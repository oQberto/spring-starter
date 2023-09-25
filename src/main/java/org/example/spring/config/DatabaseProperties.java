package org.example.spring.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.List;
import java.util.Map;

/**
 * Также данные классы можно заменить на record,
 * но тогда надо будет убрать аннотацию @ConstructorBinding
 * <p></p>
 * <p>
 * {@code @ConfigurationProperties} - это аннотация в Spring Framework, которая используется для привязки значений свойств из
 * файла конфигурации (например, application.properties или application.yml) к полям класса. Она позволяет удобно
 * настраивать приложение, а также инжектировать настройки в Java-код без явного чтения свойств из файла конфигурации.
 * <p>
 * Основные характеристики и использование аннотации @ConfigurationProperties:
 * <p>
 * Привязка свойств к классу: @ConfigurationProperties позволяет определить класс, который будет служить контейнером для
 * свойств. Этот класс обычно содержит поля, каждое из которых соответствует свойству, которое вы хотите прочитать из
 * файла конфигурации.
 * <p>
 * Префиксы и группировка: Вы можете использовать аннотацию @ConfigurationProperties с префиксом, чтобы группировать
 * свойства в разные разделы файла конфигурации и четко указать, какие именно свойства привязываются к классу.
 * <p>
 * Автоматическая инъекция: Класс, помеченный @ConfigurationProperties, будет автоматически создан Spring контейнером,
 * и свойства будут автоматически инжектированы в его поля из файла конфигурации.
 */
@Value
@ConfigurationProperties(prefix = "db")
public class DatabaseProperties {
    String username;
    String password;
    String driver;
    String url;
    String hosts;
    PoolProperties pool;
    List<PoolProperties> pools;
    Map<String, Object> properties;

    /**
     * {@code @ConstructorBinding} - это аннотация в Spring Framework, которая используется вместе с аннотацией
     * {@code @ConfigurationProperties} для указания, что значения свойств конфигурации должны быть связаны с конструктором
     * класса вместо сеттеров или полей. Это предоставляет неизменяемость (immutability) для объектов, созданных из
     * конфигурации, что является хорошей практикой для обеспечения безопасности и предсказуемости конфигурации.
     * <p>
     * Основные характеристики и использование аннотации @ConstructorBinding:
     * <p>
     * Использование конструктора: При помощи @ConstructorBinding вы определяете конструктор класса, который будет
     * использоваться для создания экземпляров этого класса на основе значений из файла конфигурации.
     * <p>
     * Неизменяемые объекты: После создания экземпляра такого класса, его состояние не может быть изменено, что делает
     * его неизменяемым. Это предостерегает от случайных изменений значений и обеспечивает безопасность в многопоточной среде.
     * <p>
     * Автоматическое связывание: Spring автоматически связывает значения свойств из файла конфигурации с параметрами
     * конструктора, основываясь на именах параметров и их соответствующих ключей в файле конфигурации.
     */
    @ConstructorBinding
    public DatabaseProperties(String username,
                              String password,
                              String driver,
                              String url,
                              String hosts,
                              PoolProperties pool,
                              List<PoolProperties> pools,
                              Map<String, Object> properties) {
        this.username = username;
        this.password = password;
        this.driver = driver;
        this.url = url;
        this.hosts = hosts;
        this.pool = pool;
        this.pools = pools;
        this.properties = properties;
    }

    @Value
    public static class PoolProperties {
        Integer size;
        Integer timeout;
    }
}
