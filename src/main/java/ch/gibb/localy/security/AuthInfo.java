package ch.gibb.localy.security;


import ch.gibb.localy.data.entity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthInfo {

    public static UserInfo getUser() {
        return (UserInfo) getAuthentication().getPrincipal();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}