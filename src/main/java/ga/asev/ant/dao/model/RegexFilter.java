package ga.asev.ant.dao.model;

import org.apache.catalina.filters.FilterBase;

public class RegexFilter extends BaseFilter<String> {

    private String regex;

    public RegexFilter(String attrId, String regex) {
        setAttrId(attrId);
        this.regex = regex;
    }

    @Override
    public boolean matches(String attrValue) {
        return !(regex == null || attrValue == null) && attrValue.matches(regex);
    }
}
