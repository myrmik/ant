package ga.asev.ant.web;

import ga.asev.ant.dao.model.User;

import javax.servlet.http.HttpSession;

public class UserContextStub implements UserContext {

    private final User userStub;

    public UserContextStub() {
        userStub = new User();
        userStub.setId("user id");
        userStub.setName("User");
    }

    public User getUser() {
        return userStub;
    }

    public void setUser(User user) {
    }
}
