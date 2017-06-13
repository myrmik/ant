package ga.asev.ant.rule.model;

import ga.asev.ant.source.model.SourceAttrValue;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static ga.asev.ant.source.model.SourceAttrValue.emptySourceAttrValue;

@Data
public class NotificationUpdateEvent {
    private String userId;
    private String sourceId;
    private String ruleId;
    private Map<String, SourceAttrValue> attributes = new HashMap<>();

    public Object getAttrValue(String attrId) {
        return attributes.getOrDefault(attrId, emptySourceAttrValue()).getValue();
    }
}
