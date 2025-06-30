package com.ufscar.pooa.backend.config;

import com.ufscar.pooa.backend.service.impl.CommentService;
import com.ufscar.pooa.backend.service.impl.NotificationService;
import com.ufscar.pooa.backend.service.impl.RatingService;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObserverPatternConfig {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private NotificationService notificationService;

    @PostConstruct
    public void registerObservers() {
        System.out.println("--- REGISTRANDO OBSERVERS (PADRÃO CLÁSSICO) ---");

        commentService.registerObserver(notificationService);
        System.out.println(">>> NotificationService está observando CommentService.");

        ratingService.registerObserver(notificationService);
        System.out.println(">>> NotificationService está observando RatingService.");

        System.out.println("--- REGISTRO DE OBSERVERS CONCLUÍDO ---");
    }
}