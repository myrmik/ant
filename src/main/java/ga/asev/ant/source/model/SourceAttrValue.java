package ga.asev.ant.source.model;

import lombok.Data;

@Data
public class SourceAttrValue {
    public static final SourceAttrValue EMPTY_VALUE = new SourceAttrValue();

    public String id;
    public SourceAttrType type;
    public Object value;

    public static SourceAttrValue emptySourceAttrValue() {
        return EMPTY_VALUE;
    }
}
