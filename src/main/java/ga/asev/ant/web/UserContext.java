package ga.asev.ant.web;

import ga.asev.ant.dao.model.User;

public interface UserContext {

    User getUser();

    void setUser(User user);
}
