package ga.asev.ant.dao.model;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

import static ga.asev.ant.dao.model.NotificationItemStatus.UNREAD;

public class NotificationItem {
    private String title;
    private String link;
    private Date pubDate;
    private NotificationItemStatus status = UNREAD;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public NotificationItemStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationItemStatus status) {
        this.status = status;
    }

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
