package ga.asev.ant.rule;

import ga.asev.ant.dao.model.Rule;
import ga.asev.ant.rule.model.NotificationUpdateEvent;
import ga.asev.ant.source.model.SourceUpdateEvent;
import ga.asev.ant.web.UserContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

import java.util.List;

public class RuleApplier {
    private final ApplicationEventPublisher publisher;
    private final RuleService ruleService;

    public RuleApplier(ApplicationEventPublisher publisher, RuleService ruleService) {
        this.publisher = publisher;
        this.ruleService = ruleService;
    }

    @EventListener
    public void onNewSourceItem(SourceUpdateEvent event) {
        List<Rule> rules = ruleService.getSourceRules(event.getSourceId());

        rules.stream()
                .filter(rule -> rule.matches(event.getAttributes()))
                .forEach(rule -> publishNotification(event, rule));
    }

    private void publishNotification(SourceUpdateEvent event, Rule rule) {
        NotificationUpdateEvent notificationEvent = new NotificationUpdateEvent();
        notificationEvent.setUserId(rule.getUserId());
        notificationEvent.setSourceId(rule.getSourceId());
        notificationEvent.setAttributes(event.getAttributes());
        publisher.publishEvent(notificationEvent);
    }
}
