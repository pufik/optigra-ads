package org.optigra.ads.security.session;

import java.util.ArrayList;
import java.util.Collection;

import org.optigra.ads.model.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Override
    public Session createSessionForUser(final User user) {
        Authentication authentication = getAuthentication(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new Session(user);
    }

    private Authentication getAuthentication(final User user) {
        Collection<GrantedAuthority> authorities = getAuthorities(user);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword(), authorities);
        token.setDetails(user);

        return token;
    }

    private Collection<GrantedAuthority> getAuthorities(final User principal) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(principal.getRole().getSecurityCode()));

        return authorities;
    }

    public User getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(final User defaultUser) {
        this.defaultUser = defaultUser;
    }
}
