package org.optigra.ads.security.session;

import org.optigra.ads.model.user.User;

public class Session {

    private final User user;

    public Session(final User user) {
        super();
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
