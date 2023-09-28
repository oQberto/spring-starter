package org.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class AuditConfiguration {

    /**
     * AuditorAware - это интерфейс в Spring Data JPA, который используется для определения текущего аудитора
     * (кем создана или изменена сущность) в контексте выполнения операции с базой данных. Этот интерфейс предоставляет
     * гибкую возможность настройки механизма аудита сущностей, позволяя определить текущего аудитора на основе вашей
     * бизнес-логики или внешних источников данных.
     * <p>
     * Интерфейс AuditorAware определяет метод T getCurrentAuditor(), который возвращает объект, представляющий текущего
     * аудитора. Тип T обычно представляет собой тип, используемый для идентификации аудитора, например, String для
     * имени пользователя или Long для идентификатора пользователя.
     * <p>
     * В конфигурации Spring вы создаете бин, который предоставляет реализацию AuditorAware. Этот бин будет
     * автоматически использоваться Spring Data JPA для определения текущего аудитора при сохранении или обновлении сущности.
     *
     * @return
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        //На практике будет применяться ->
        //SecurityContext.getCurrentUser().getEmail()
        return () -> Optional.of("alex");
    }
}
