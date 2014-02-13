package org.optigra.ads.facade.converter.user;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.model.user.User;
import org.springframework.stereotype.Component;

/**
 * Converter for converting UserDetails resource to User.
 * @date Feb 13, 2014
 * @author ivanursul
 */
@Component("userDetailsResourceConverter")
public class UserDetailsResourceConverter extends AbstractConverter<UserDetailsResource, User> {

    @Override
    public User convert(final UserDetailsResource source, final User target) {
        
        User user = new User();
        user.setId(source.getId());
        user.setLogin(source.getLogin());
        user.setPassword(source.getPassword());
        
        return user;
    }

    @Override
    public User convert(final UserDetailsResource source) {
        return convert(source, new User());
    }

}