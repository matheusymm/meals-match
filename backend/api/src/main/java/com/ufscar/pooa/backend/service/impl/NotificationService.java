package com.ufscar.pooa.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import com.persistence.PersistenceFramework;
import com.ufscar.pooa.backend.dto.NotificationDTO;
import com.ufscar.pooa.backend.events.NewCommentEvent;
import com.ufscar.pooa.backend.events.NewRatingEvent;
import com.ufscar.pooa.backend.model.Notification;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.model.Notification;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.service.interfaces.INotificationService;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private final PersistenceFramework notificationPersistence;

    public NotificationService(PersistenceFramework notificationPersistenceFramework) {
        this.notificationPersistence = notificationPersistenceFramework;
    }

    @Override
    @EventListener
    public void handleNewRating(NewRatingEvent ratingEvent) {

        Recipe recipe = ratingEvent.getRating().getRecipe();
        User notifiedUser = recipe.getAuthor();
        User madeRating = ratingEvent.getRating().getAuthor();

        if (!madeRating.getId().equals(notifiedUser.getId())) {
            Notification notification = new Notification();

            notification.setId(UUID.randomUUID());
            notification.setRecipientId(notifiedUser.getId());
         //   notification.setCreatedAt(new Date());
            notification.setRead(false);

            String message = String.format(
                    "Usuário " + notifiedUser.getName() + ", o usuário " + madeRating.getName()
                            + " fez uma nova avaliação na sua receita " + recipe.getName());
            notification.setMessage(message);

            try {
                System.out.println("--- Usando o Framework de Persistência Customizado ---");
                notificationPersistence.insert(notification);
                System.out.println("Notificação salva no banco! ID: " + notification.getId());
            } catch (Exception e) {
                System.err.println("Falha ao salvar notificação com o framework customizado.");
                e.printStackTrace();
            }
        }
    }

    @Override
    @EventListener
    public void handleNewComment(NewCommentEvent commentEvent) {
        
        Recipe recipe = commentEvent.getComment().getRecipe();
        User notifiedUser = recipe.getAuthor();
        User madeComment = commentEvent.getComment().getAuthor();

        if (!madeComment.getId().equals(notifiedUser.getId())) {
            Notification notification = new Notification();

            notification.setId(UUID.randomUUID());
            notification.setRecipientId(notifiedUser.getId());
         //   notification.setCreatedAt(new Date());
            notification.setRead(false);

            String message = String.format(
                    "Usuário " + notifiedUser.getName() + ", o usuário " + madeComment.getName()
                            + " fez uma nova postagem na sua receita " + recipe.getName());
            notification.setMessage(message);

            try {
                System.out.println("--- Usando o Framework de Persistência Customizado ---");
                notificationPersistence.insert(notification);
                System.out.println("Notificação salva no banco! ID: " + notification.getId());
            } catch (Exception e) {
                System.err.println("Falha ao salvar notificação com o framework customizado.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<NotificationDTO> getAllNotifications() {
          List<Object> objectList = notificationPersistence.findAll();

   
        List<Notification> notifications = objectList.stream()
                .map(obj -> (Notification) obj) // Para cada Object, converta-o para Notification
                .collect(Collectors.toList());

        return notifications.stream()
                .map(notification -> new NotificationDTO(
                        notification.getId(),
                        notification.getMessage(),
                        notification.getRecipientId(),
                        notification.isRead()
                ))
                .toList();
     }
}
