package ga.asev.ant.notification;

import ga.asev.ant.dao.model.Notification;
import ga.asev.ant.dao.repository.NotificationRepository;
import org.springframework.data.domain.Example;

import static ga.asev.ant.dao.model.Notification.emptyNotification;
import static java.util.stream.Collectors.toSet;

public class NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification getRuleNotifications(String userId, String ruleId) {
        Notification notification = getUserNotifications(userId);
        notification.setItems(
                notification.getItems().stream()
                        .filter(it -> ruleId.equals(it.getRuleId()))
                        .collect(toSet())
        );
        return notification;
    }

    public Notification getUserNotifications(String userId) {
        Notification probe = emptyNotification();
        probe.setUserId(userId);
        return getNotificationByExample(probe);
    }

    private Notification getNotificationByExample(Notification probe) {
        Notification notification = notificationRepository.findOne(Example.of(probe));
        if (notification != null) {
            return notification;
        }

        return new Notification();
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(String notificationId) {
        notificationRepository.delete(notificationId);
    }
}
