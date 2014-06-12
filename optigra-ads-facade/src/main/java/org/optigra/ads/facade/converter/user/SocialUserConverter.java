package org.optigra.ads.facade.converter.user;

import java.util.UUID;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;
import org.optigra.ads.social.model.SocialUser;
import org.springframework.stereotype.Component;

@Component("socialUserConverter")
public class SocialUserConverter extends AbstractConverter<SocialUser, User> {

    @Override
    public User convert(final SocialUser source) {
        return convert(source, new User());
    }

    @Override
    public User convert(final SocialUser source, final User target) {
        target.setLogin(source.getId());
        target.setFullName(source.getName());
        target.setRole(UserRole.USER);
        target.setEmail(source.getId());
        target.setPassword(generatePassword());

        return target;
    }

    private String generatePassword() {
        return UUID.randomUUID().toString();
    }
}
