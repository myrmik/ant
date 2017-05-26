package ga.asev.ant.dao.model;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationItem {
    private String title;
    private String link;
    private Date pubDate;
}
