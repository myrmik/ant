package ga.asev.ant.web.rest;

import ga.asev.ant.dao.model.NotificationItem;
import ga.asev.ant.dao.model.Rule;
import ga.asev.ant.notification.NotificationService;
import ga.asev.ant.rule.RuleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/rule")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @RequestMapping(path = "/user/rules")
    public List<NotificationItem> getUserNotifications(Long userId) {
        return notificationService.getUserNotifications(userId);
    }


}
