package ga.asev.ant.notification;

import ga.asev.ant.dao.model.Notification;
import ga.asev.ant.dao.model.NotificationItem;
import ga.asev.ant.dao.repository.NotificationRepository;

import java.util.List;

import static java.util.Collections.emptyList;

public class NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationItem> getUserNotifications(Long userId) {
        Notification notification = notificationRepository.findOne(userId);
        return notification == null ? emptyList() : notification.getItems();
    }
}
