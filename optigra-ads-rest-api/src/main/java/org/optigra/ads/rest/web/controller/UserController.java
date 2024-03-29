package org.optigra.ads.rest.web.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.facade.resource.user.UserResource;
import org.optigra.ads.facade.user.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for User API functions.
 * @date Feb 7, 2014
 * @author ivanursul
 *
 */
@Controller
@RequestMapping(value = ResourceUri.USER)
public class UserController extends BaseController {

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public PagedResultResource<UserResource> getUsers(@RequestParam(value = "offset", defaultValue = "0") final int offset,
            @RequestParam(value = "limit", defaultValue = "20") final int limit) {
        return userFacade.getUsers(offset, limit);
    }

    @RequestMapping(value = ResourceUri.USER_BY_ID, method = RequestMethod.GET)
    @ResponseBody
    public UserResource getUser(@PathVariable("id") final Long id) {
        return userFacade.getUser(id);
    }

    @RequestMapping(value = ResourceUri.CURRENT, method = RequestMethod.GET)
    @ResponseBody
    public UserResource getCurrentUser() {
    	return userFacade.getCurrentUser();
    }

    @RequestMapping(value = ResourceUri.USER_BY_ID ,method = RequestMethod.PUT)
    @ResponseBody
    public MessageResource updateUser(@PathVariable("id") final Long userId, @Valid @RequestBody final UserDetailsResource userResource) {
    	userFacade.updateUser(userId, userResource);
    	return new MessageResource(MessageType.INFO, "User updated");
    }

    @RequestMapping(value = ResourceUri.USER_BY_ID ,method = RequestMethod.DELETE)
    @ResponseBody
    public MessageResource deleteUser(@PathVariable("id") final Long userId) {
    	userFacade.deleteUser(userId);
    	return new MessageResource(MessageType.INFO, "User deleted");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public UserResource createUser(@Valid @RequestBody final UserDetailsResource userResource) {
        return userFacade.createUser(userResource);
    }

}
