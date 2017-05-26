package ga.asev.ant.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSession;

@Configuration
public class WebConfig {

    @Bean
    public UserContext userContext(HttpSession httpSession) {
        return new UserContext(httpSession);
    }
}
