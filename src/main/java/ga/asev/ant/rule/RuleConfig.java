package ga.asev.ant.rule;

import ga.asev.ant.dao.repository.RuleRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleConfig {
    @Bean
    public RuleService ruleService(RuleRepository ruleRepository) {
        return new RuleService(ruleRepository);
    }

    @Bean
    public RuleApplier ruleApplier(ApplicationEventPublisher publisher, RuleService ruleService) {
        return new RuleApplier(publisher, ruleService);
    }
}
