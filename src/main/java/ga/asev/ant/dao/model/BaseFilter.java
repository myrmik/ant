package ga.asev.ant.dao.model;

public abstract class BaseFilter<T> implements Filter<T> {
    private String attrId;

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }
}
