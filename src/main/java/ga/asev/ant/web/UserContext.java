package ga.asev.ant.web;

import ga.asev.ant.dao.model.User;

import javax.servlet.http.HttpSession;

public class UserContext {
    private static final String USER_ATTR_KEY = "USER_ATTR_KEY";
    private HttpSession httpSession;

    public UserContext(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public User getUser() {
        return (User) httpSession.getAttribute(USER_ATTR_KEY);
    }

    public void setUser(User user) {
        httpSession.setAttribute(USER_ATTR_KEY, user);
    }
}
