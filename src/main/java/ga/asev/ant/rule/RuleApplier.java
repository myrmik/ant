package ga.asev.ant.rule;

import ga.asev.ant.dao.model.Rule;
import ga.asev.ant.rule.model.NotificationUpdateEvent;
import ga.asev.ant.source.model.SourceUpdateEvent;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

import java.util.List;

import static ga.asev.ant.util.DateUtil.toDate;
import static java.util.stream.Collectors.toList;

@Log
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

        List<Rule> matchedRules = rules.stream()
                .filter(rule -> rule.matches(event.getAttributes()))
                .collect(toList());

        updateRules(event, matchedRules);

        publishNotifications(event, matchedRules);
    }

    private void updateRules(SourceUpdateEvent event, List<Rule> rules) {
        rules.forEach(rule -> rule.setPubDate(toDate(event.getAttrValue("pubDate"))));
        ruleService.saveRules(rules);
    }

    private void publishNotifications(SourceUpdateEvent event, List<Rule> rules) {
        rules.forEach(rule -> publishNotification(event, rule));
    }

    private void publishNotification(SourceUpdateEvent event, Rule rule) {
        NotificationUpdateEvent notificationEvent = new NotificationUpdateEvent();
        notificationEvent.setUserId(rule.getUserId());
        notificationEvent.setSourceId(rule.getSourceId());
        notificationEvent.setAttributes(event.getAttributes());
        notificationEvent.setRuleId(rule.getId());
        publisher.publishEvent(notificationEvent);
    }
}
