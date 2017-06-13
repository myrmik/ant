package ga.asev.ant.dao.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document
@Data
public class Notification {
    private String id;
    private String userId;
    private Set<NotificationItem> items = new HashSet<>();

    public static Notification emptyNotification() {
        Notification notification = new Notification();
        notification.setItems(null);
        return notification;
    }
}
