package ga.asev.ant.web.rest;

import ga.asev.ant.dao.model.Rule;
import ga.asev.ant.rule.RuleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/rule")
public class RuleController {

    private RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @RequestMapping(path = "/rule", method = POST)
    public void addRule(Rule rule) {
        ruleService.addRule(rule);
    }

    @RequestMapping(path = "/rule", method = DELETE)
    public void deleteRule(Long ruleId) {
        ruleService.deleteRule(ruleId);
    }

    @RequestMapping(path = "/user/rules")
    public List<Rule> getUserRules(Long userId) {
        return ruleService.getUserRules(userId);
    }


}
