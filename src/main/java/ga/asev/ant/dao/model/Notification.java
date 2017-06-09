package ga.asev.ant.dao.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Notification {
    private String id;
    private String userId;
    private List<NotificationItem> items;
}
