package org.optigra.ads.facade.user;

import javax.annotation.Resource;

import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.facade.resource.user.UserResource;
import org.optigra.ads.model.user.User;
import org.optigra.ads.service.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Facade for user operations.
 * @date Feb 7, 2014
 * @author ivanursul
 *
 */
@Component("userFacade")
@Transactional
public class DefaultUserFacade implements UserFacade {

    @Resource(name = "userService")
    private UserService userService;
    
    @Resource(name = "userConverter")
    private Converter<User, UserResource> userConverter;

    @Resource(name = "userDetailsResourceConverter")
    private Converter<UserDetailsResource, User> userDetailsResourceConverter;
    
    @Override
    public UserResource getUserById(final Long id) {
        
        User user = userService.getUserById(id);
        UserResource userResource = userConverter.convert(user);
        
        return userResource;
    }

    @Override
    public void createUser(final UserDetailsResource userResource) {

        User user = userDetailsResourceConverter.convert(userResource);

        userService.createUser(user);
    }

}
