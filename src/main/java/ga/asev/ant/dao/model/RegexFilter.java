package ga.asev.ant.dao.model;

public class RegexFilter extends BaseFilter {

    private String regex;

    public RegexFilter() {
    }

    public RegexFilter(String attrId, String regex) {
        setAttrId(attrId);
        setRegex(regex);
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean matches(Object attrValue) {
        return !(regex == null || attrValue == null) && attrValue.toString().matches(regex);
    }
}
