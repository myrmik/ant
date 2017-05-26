package ga.asev.ant.dao.model;

import ga.asev.ant.source.model.SourceAttrValue;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document
@Data
public class Rule {
    private Long id;
    private Long userId;
    private String sourceId;
    private List<Filter> filters = new ArrayList<>();

    public boolean matches(Map<String, SourceAttrValue> attributes) {
        return filters.stream().allMatch(filter -> {
            SourceAttrValue attr = attributes.get(filter.getAttrId());
            return attr == null || filter.matches(attr.getValue());
        });
    }
}
