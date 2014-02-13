package org.optigra.ads.facade.converter.user;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.user.UserResource;
import org.optigra.ads.model.user.User;
import org.springframework.stereotype.Component;

/**
 * Converter for User-UserResource relation.
 * 
 * @date Feb 7, 2014
 * @author ivanursul
 * 
 */
@Component("userConverter")
public class UserConverter extends AbstractConverter<User, UserResource> {

    @Override
    public UserResource convert(final User user) {
        return convert(user, new UserResource());
    }

    @Override
    public UserResource convert(final User user, final UserResource target) {

        target.setId(user.getId());
        target.setLogin(user.getLogin());

        return target;
    }

}
