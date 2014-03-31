package org.optigra.ads.security.session;

import org.optigra.ads.model.user.User;
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

    @Override
    public Session getCurrentSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getDetails();

        if (principal instanceof User) {
            return new Session((User) principal);
        }

        throw new IllegalArgumentException();
    }
}
