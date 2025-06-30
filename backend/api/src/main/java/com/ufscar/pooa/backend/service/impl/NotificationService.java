package com.ufscar.pooa.backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.persistence.PersistenceFramework;
import com.ufscar.pooa.backend.dto.Notification.NotificationDTOFactory;
import com.ufscar.pooa.backend.dto.Notification.NotificationDetailDTO;
import com.ufscar.pooa.backend.model.Comment;
import com.ufscar.pooa.backend.model.Notification;
import com.ufscar.pooa.backend.model.Rating;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.observer.Observer;
import com.ufscar.pooa.backend.service.interfaces.INotificationService;

@Service
public class NotificationService implements INotificationService, Observer {
    private final PersistenceFramework notificationPersistence;

    public NotificationService(PersistenceFramework notificationPersistenceFramework) {
        this.notificationPersistence = notificationPersistenceFramework;
    }

    @Override
    public void update(Object data) {

        if (data instanceof Comment) {
            handleNewComment((Comment) data);
        } else if (data instanceof Rating) {
            handleNewRating((Rating) data);
        }
    }

    @Override
    public void handleNewRating(Rating rating) {

        Recipe recipe = rating.getRecipe();
        User notifiedUser = recipe.getAuthor();
        User madeRating = rating.getAuthor();

        if (!madeRating.getId().equals(notifiedUser.getId())) {

            Notification notification = new Notification();
            notification.setId(UUID.randomUUID());
            notification.setRecipientId(notifiedUser.getId());
            notification.setRead(false);

            String message = String.format(
                    "Usuário " + notifiedUser.getName() + ", o usuário " + madeRating.getName()
                            + " fez uma nova avaliação na sua receita " + recipe.getName());
            notification.setMessage(message);

            try {
                System.out.println("--- Usando o Framework de Persistência Customizado ---");
                notificationPersistence.insert(notification);
                System.out.println("Notificação de avaliação salva no banco! ID: " + notification.getId());
            } catch (Exception e) {
                System.err.println("Falha ao salvar notificação com o framework customizado.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handleNewComment(Comment comment) {

        Recipe recipe = comment.getRecipe();
        User notifiedUser = recipe.getAuthor();
        User madeComment = comment.getAuthor();

        if (!madeComment.getId().equals(notifiedUser.getId())) {

            Notification notification = new Notification();
            notification.setId(UUID.randomUUID());
            notification.setRecipientId(notifiedUser.getId());
            notification.setRead(false);

            String message = String.format(
                    "Usuário " + notifiedUser.getName() + ", o usuário " + madeComment.getName()
                            + " fez uma nova postagem na sua receita " + recipe.getName());
            notification.setMessage(message);

            try {
                System.out.println("--- Usando o Framework de Persistência Customizado ---");
                notificationPersistence.insert(notification);
                System.out.println("Notificação de comentário salva no banco! ID: " + notification.getId());
            } catch (Exception e) {
                System.err.println("Falha ao salvar notificação com o framework customizado.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<NotificationDetailDTO> getAllNotifications() {
        List<Object> objectList = notificationPersistence.findAll();

        List<Notification> notifications = objectList.stream()
                .map(obj -> (Notification) obj)
                .toList();

        return notifications.stream()
                .map(NotificationDTOFactory::toDetailDTO)
                .toList();
    }
}
