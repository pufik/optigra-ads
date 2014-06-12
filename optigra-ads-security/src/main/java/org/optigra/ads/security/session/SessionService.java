package org.optigra.ads.security.session;

import org.optigra.ads.model.user.User;

public interface SessionService {

    /**
     * Provides current session for user
     *
     * @date Jun 12, 2014
     * @return {@link Session}
     */
    Session getCurrentSession();

    /**
     * Create session for user
     *
     * @param user
     *            - system user
     * @date Jun 12, 2014
     * @return {@link Session}
     */

    Session createSessionForUser(User user);

}
