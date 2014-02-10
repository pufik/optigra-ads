package org.optigra.ads.facade.user;

import javax.annotation.Resource;

import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.dto.UserResource;
import org.optigra.ads.model.user.User;
import org.optigra.ads.service.user.UserService;
import org.springframework.stereotype.Component;

/** Facade for user operations.
 * @date Feb 7, 2014
 * @author ivanursul
 *
 */
@Component
public class DefaultUserFacade implements UserFacade {

    @Resource
    private UserService defaultUserService;
    
    @Resource 
    private Converter<User, UserResource> userConverter;
    
    @Override
    public UserResource getUserById(final Long id) {
        
        User user = defaultUserService.getUserById(id);
        UserResource userResource = userConverter.convert(user);
        
        return userResource;
    }

}
