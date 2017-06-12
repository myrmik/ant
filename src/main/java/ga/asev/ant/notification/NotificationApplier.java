package ga.asev.ant.notification;

import ga.asev.ant.dao.model.Notification;
import ga.asev.ant.dao.model.NotificationItem;
import ga.asev.ant.dao.repository.NotificationRepository;
import ga.asev.ant.rule.model.NotificationUpdateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class NotificationApplier {
    private static final int FEED_SIZE = 100;

    private NotificationService notificationService;

    public NotificationApplier(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @EventListener
    public void onNotification(NotificationUpdateEvent event) {
        Notification notification = getUserNotification(event.getUserId());
        notification.setUserId(event.getUserId());
        notification.getItems().add(toNotificationItem(event));
        clearExtraItems(notification);
        notificationService.saveNotification(notification);
    }

    private Notification getUserNotification(String userId) {
        return notificationService.getUserNotifications(userId);
    }

    private void clearExtraItems(Notification notification) {
        if (notification.getItems().size() <= FEED_SIZE) {
            return;
        }

        Set<NotificationItem> cleared = notification.getItems().stream()
                .sorted((o1, o2) -> o2.getPubDate().compareTo(o1.getPubDate()))
                .limit(FEED_SIZE)
                .collect(toSet());
        notification.setItems(cleared);
    }

    private NotificationItem toNotificationItem(NotificationUpdateEvent event) {
        NotificationItem item = new NotificationItem();
        item.setTitle(toStr(event.getAttrValue("title")));
        item.setLink(toStr(event.getAttrValue("link")));
        item.setPubDate(toDate(event.getAttrValue("pubDate")));
        return item;
    }

    private Date toDate(Object value) {
        DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        return  (Date) value;
    }

    private String toStr(Object value) {
        return Objects.toString(value, null);
    }

}
