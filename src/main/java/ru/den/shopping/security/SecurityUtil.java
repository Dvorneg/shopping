package ru.den.shopping.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.den.shopping.model.User;

public class SecurityUtil  {

    public static User getLoggedUser() {

        User user = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth;
        if (securityContext != null) {
            auth = securityContext.getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal instanceof UserDetailsImpl) {
                    UserDetailsImpl authUser = (UserDetailsImpl) principal;
                    user = authUser.getUser();
                }
            }
        }
        return user;
    }

}
