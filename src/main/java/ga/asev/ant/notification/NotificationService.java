package ga.asev.ant.notification;

import ga.asev.ant.dao.model.Notification;
import ga.asev.ant.dao.model.NotificationItem;
import ga.asev.ant.dao.model.User;
import ga.asev.ant.dao.repository.NotificationRepository;
import org.springframework.data.domain.Example;

import java.util.List;

import static java.util.Collections.emptyList;

public class NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationItem> getUserNotifications(String userId) {
        Notification probe = new Notification();
        probe.setUserId(userId);
        Notification notification = notificationRepository.findOne(Example.of(probe));
        return notification == null ? emptyList() : notification.getItems();
    }
}
