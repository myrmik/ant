package ga.asev.ant.notification;

import ga.asev.ant.dao.repository.NotificationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {
    @Bean
    public NotificationService notificationService(NotificationRepository notificationRepository) {
        return new NotificationService(notificationRepository);
    }

    @Bean
    public NotificationApplier notificationApplier(NotificationRepository notificationRepository) {
        return new NotificationApplier(notificationRepository);
    }
}
