package org.optigra.ads.facade.user;

import java.util.List;

import javax.annotation.Resource;

import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.facade.resource.user.UserResource;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.model.user.User;
import org.optigra.ads.security.session.SessionService;
import org.optigra.ads.service.user.UserService;
import org.optigra.ads.social.model.SocialUser;
import org.optigra.ads.social.service.SocialUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Facade for user operations.
 *
 * @date Feb 7, 2014
 * @author ivanursul
 */
@Component("userFacade")
@Transactional
public class DefaultUserFacade implements UserFacade {

    private static final Logger logger = LoggerFactory.getLogger(DefaultUserFacade.class);

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Resource(name = "socialUserService")
    private SocialUserService socialUserService;

    @Resource(name = "userConverter")
    private Converter<User, UserResource> userConverter;

    @Resource(name = "userDetailsResourceConverter")
    private Converter<UserDetailsResource, User> userDetailsResourceConverter;

    @Resource(name = "socialUserConverter")
    private Converter<SocialUser, User> socialUserConverter;

    @Resource(name = "putUserDetailsResourceConverter")
    private Converter<UserDetailsResource, User> putUserDetailsResourceConverter;

    @Resource(name = "pagedSearchConverter")
    private Converter<PagedResult<?>, PagedResultResource<? extends org.optigra.ads.facade.resource.Resource>> pagedSearchConverter;

    @Override
    public UserResource getUser(final Long id) {

        User user = userService.getUserById(id);
        UserResource userResource = userConverter.convert(user);

        return userResource;
    }

    @Override
    public UserResource getCurrentUser() {

        User user = sessionService.getCurrentSession().getUser();
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
    public void updateUser(final Long userId, final UserDetailsResource userResource) {
        User user = userService.getUserById(userId);
        putUserDetailsResourceConverter.convert(userResource, user);
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
    public void deleteUser(final Long userId) {
        userService.deleteUser(userId);
    }

    @Override
    public UserResource authorizeUserByLoginAndPassword(final String login, final String password) {
        User user = userService.getUserByLoginAndPassword(login, password);

        sessionService.createSessionForUser(user);

        return userConverter.convert(user);
    }

    @Override
    public UserResource authorizeSocialUserByCode(final String accessCode) {
        SocialUser socialUser = socialUserService.getUserByOAuthCode(accessCode);

        return createSessionForSocialUser(socialUser);
    }

    @Override
    public UserResource authorizeSocialUserByToken(final String token) {
        SocialUser socialUser = socialUserService.getUserByOAuthToken(token);

        return createSessionForSocialUser(socialUser);
    }

    protected UserResource createSessionForSocialUser(final SocialUser socialUser) {
        User user = getUserForSocialUser(socialUser);

        sessionService.createSessionForUser(user);

        return userConverter.convert(user);
    }

    protected User getUserForSocialUser(final SocialUser socialUser) {
        Assert.notNull(socialUser, "Social user couldn't be null");

        User user = null;

        try {
            user = userService.getUserByLogin(socialUser.getId());
        } catch (Exception e) {
            logger.warn("User doesn't exists", e);
            logger.info("Create user for social user: {}", socialUser);

            user = socialUserConverter.convert(socialUser);
            userService.createUser(user);
        }

        return user;
    }

}
