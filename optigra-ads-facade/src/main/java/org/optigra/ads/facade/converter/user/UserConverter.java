package org.optigra.ads.facade.converter.user;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.dto.UserResource;
import org.optigra.ads.model.user.User;
import org.springframework.stereotype.Component;

/**
 * Converter for User-UserResource relation.
 * @date Feb 7, 2014
 * @author ivanursul
 *
 */
@Component
public class UserConverter extends AbstractConverter<User, UserResource> {

    @Override
    public UserResource convert(final User user) {
        UserResource resource = new UserResource();
        
        resource.setId(user.getId());
        resource.setLogin(user.getLogin());
        
        return resource;
    }

}
