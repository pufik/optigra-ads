package org.optigra.ads.facade.user;

import java.util.List;

import javax.annotation.Resource;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
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
    
    @Resource(name = "pagedSearchConverter")
    private Converter<PagedResult<?>, PagedResultResource<? extends org.optigra.ads.facade.resource.Resource>> pagedSearchConverter;
    
    @Override
    public UserResource getUserById(final Long id) {
        
        User user = userService.getUserById(id);
        UserResource userResource = userConverter.convert(user);
        
        return userResource;
    }

    @Override
    public UserResource createUser(final UserDetailsResource userResource) {
        User user = userDetailsResourceConverter.convert(userResource);
        userService.createUser(user);
        return userConverter.convert(user);
    }

	@Override
	public void updateUser(Long userId, UserDetailsResource userResource) {
		User user = userService.getUserById(userId);
		userDetailsResourceConverter.convert(userResource, user);
		userService.update(user);
	}

    
    @Override
    public PagedResultResource<UserResource> getUsers(final int offset, final int limit) {
        
        PagedResult<User> pagedResult = userService.getUsers(offset, limit);
        
        List<UserResource> userResources = userConverter.convertAll(pagedResult.getEntities());
        
        PagedResultResource<UserResource> pagedResultResource = new PagedResultResource<>(ResourceUri.USER);
        pagedResultResource.setEntities(userResources);
        
        pagedSearchConverter.convert(pagedResult, pagedResultResource);
        
        return pagedResultResource;
    }

	@Override
	public void deleteUser(Long userId) {
		userService.deleteUser(userId);
	}
    
}
