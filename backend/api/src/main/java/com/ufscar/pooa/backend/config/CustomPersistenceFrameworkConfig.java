package com.ufscar.pooa.backend.config;

import com.persistence.PersistenceFramework;
import com.ufscar.pooa.backend.model.Notification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomPersistenceFrameworkConfig {

    @Bean
    public PersistenceFramework notificationPersistenceFramework() {
        return new PersistenceFramework(Notification.class);
    }
}
