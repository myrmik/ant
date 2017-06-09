package ga.asev.ant.dao.model;

public interface Filter {
    String getAttrId();
    boolean matches(Object attrValue);
}
