package org.optigra.ads.security.session;

import org.optigra.ads.model.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Session service for web-based session
 *
 * @date Mar 24, 2014
 * @author Iurii Parfeniuk
 */
public class WebSessionService implements SessionService {

    private User defaultUser;

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
            }
        }

        return principal;
    }

    public User getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(final User defaultUser) {
        this.defaultUser = defaultUser;
    }
}
