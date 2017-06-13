package ga.asev.ant.dao.model;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

import static ga.asev.ant.dao.model.NotificationItemStatus.UNREAD;

@Data
public class NotificationItem {
    private String ruleId;
    private String title;
    private String link;
    private Date pubDate;
    private NotificationItemStatus status = UNREAD;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationItem that = (NotificationItem) o;
        return Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }
}
