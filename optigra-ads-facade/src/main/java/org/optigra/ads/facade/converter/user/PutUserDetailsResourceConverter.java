package org.optigra.ads.facade.converter.user;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.model.user.User;
import org.springframework.stereotype.Component;

/**
 * Converter for converting UserDetails resource to User.
 *
 * @date Feb 13, 2014
 * @author ivanursul
 */
@Component("putUserDetailsResourceConverter")
public class PutUserDetailsResourceConverter extends AbstractConverter<UserDetailsResource, User> {

    @Override
    public User convert(final UserDetailsResource source, final User target) {
        
    	target.setLogin(source.getLogin());
        target.setRole(source.getRole());
        target.setEmail(source.getEmail());
        target.setFullName(source.getFullName());
        target.setImageUrl(source.getImageUrl());

        return target;
    }

    @Override
    public User convert(final UserDetailsResource source) {
        return convert(source, new User());
    }
}
