package ga.asev.ant.dao.model;

public interface Filter<T> {
    String getAttrId();
    boolean matches(T attrValue);
}
