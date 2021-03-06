package ga.asev.ant.web.rest;

import ga.asev.ant.dao.model.Notification;
import ga.asev.ant.dao.model.NotificationItem;
import ga.asev.ant.dao.model.Rule;
import ga.asev.ant.dao.model.User;
import ga.asev.ant.notification.NotificationService;
import ga.asev.ant.rule.RuleService;
import ga.asev.ant.web.UserContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UiController {

    private RuleService ruleService;
    private NotificationService notificationService;
    private UserContext userContext;


    public UiController(RuleService ruleService, NotificationService notificationService, UserContext userContext) {
        this.ruleService = ruleService;
        this.notificationService = notificationService;
        this.userContext = userContext;
    }

    @RequestMapping(path = "/rule", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addRule(@RequestBody Rule rule) {
        ruleService.addRule(rule);
    }

    @RequestMapping(path = "/rule/{ruleId}", method = DELETE)
    public void deleteRule(@PathVariable String ruleId) {
        ruleService.deleteRule(ruleId);
    }

    @RequestMapping(path = "/rule/{ruleId}/notifications")
    public Set<NotificationItem> getRuleNotifications(@PathVariable String ruleId) {
        User user = userContext.getUser();
        return notificationService.getRuleNotifications(user.getId(), ruleId).getItems();
    }


    @RequestMapping(path = "/user/rules")
    public List<Rule> getUserRules() {
        User user = userContext.getUser();
        return ruleService.getUserRules(user.getId());
    }

    @RequestMapping(path = "/user/notifications")
    public Set<NotificationItem> getUserNotifications() {
        User user = userContext.getUser();
        return notificationService.getUserNotifications(user.getId()).getItems();
    }

    @RequestMapping(path = "/user/notification/{notificationId}", method = DELETE)
    public void deleteNotification(@PathVariable String notificationId) {
        notificationService.deleteNotification(notificationId);
    }

}
