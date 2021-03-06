package ga.asev.ant.source.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static ga.asev.ant.source.model.SourceAttrValue.emptySourceAttrValue;

public class SourceUpdateEvent {
    @Getter private String sourceId;
    @Getter private Map<String, SourceAttrValue> attributes = new HashMap<>();

    public SourceUpdateEvent(String sourceId) {
        this.sourceId = sourceId;
    }

    public void addAttr(Source.SourceAttr attr, Object value) {
        SourceAttrValue updateItem = new SourceAttrValue();
        updateItem.setId(attr.getId());
        updateItem.setType(attr.getType());
        updateItem.setValue(value);
        getAttributes().put(attr.getId(), updateItem);
    }


    public Object getAttrValue(String attrId) {
        return attributes.getOrDefault(attrId, emptySourceAttrValue()).getValue();
    }
}
