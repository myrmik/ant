package ga.asev.ant.dao.repository;

import ga.asev.ant.dao.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
