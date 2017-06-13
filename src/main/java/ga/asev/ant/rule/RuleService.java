package ga.asev.ant.rule;

import ga.asev.ant.dao.model.Filter;
import ga.asev.ant.dao.model.Rule;
import ga.asev.ant.dao.repository.RuleRepository;
import org.springframework.data.domain.Example;

import java.util.List;

import static ga.asev.ant.dao.model.Rule.emptyRule;

public class RuleService {
    private RuleRepository ruleRepository;

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public void addRule(Rule rule) {
        ruleRepository.insert(rule);
    }

    public void deleteRule(String ruleId) {
        ruleRepository.delete(ruleId);
    }

    public List<Rule> getUserRules(String userId) {
        Rule probe = emptyRule();
        probe.setUserId(userId);
        return ruleRepository.findAll(Example.of(probe));
    }

    public List<Rule> getSourceRules(String sourceId) {
        Rule probe = emptyRule();
        probe.setSourceId(sourceId);
        return ruleRepository.findAll(Example.of(probe));
    }

    public List<Rule> getRules(String userId, String sourceId) {
        Rule probe = emptyRule();
        probe.setUserId(userId);
        probe.setSourceId(sourceId);
        return ruleRepository.findAll(Example.of(probe));
    }

    public void saveRules(List<Rule> rules) {
        ruleRepository.save(rules);
    }
}
