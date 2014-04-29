package org.optigra.ads.security.session;

import javax.annotation.PostConstruct;

import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Session service for web-based session
 *
 * @date Mar 24, 2014
 * @author Iurii Parfeniuk
 */
@Component("sessionService")
public class WebSessionService implements SessionService {

    private User defaultUser;

    @PostConstruct
    public void init() {
        defaultUser = new User();
        defaultUser.setRole(UserRole.ANONYMOUS);
    }

    @Override
    public Session getCurrentSession() {
        return new Session(getPrincipal());
    }

    // TODO: IP - Temporary work around
    private User getPrincipal() {
        User principal = defaultUser;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object userDetails = auth.getDetails();
            if (userDetails instanceof User) {
                principal = (User) userDetails;
            } else {
                throw new IllegalArgumentException();
            }
        }

        return principal;
    }
}
