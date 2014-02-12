package org.optigra.ads.rest.web.controller;

import javax.annotation.Resource;

import org.optigra.ads.facade.dto.ResourceUri;
import org.optigra.ads.facade.dto.UserResource;
import org.optigra.ads.facade.user.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Resource
    private UserFacade defaultUserFacade;
    
    @RequestMapping(value = "/{id}")
    @ResponseBody
    public UserResource getUser(@PathVariable("id") final Long id) {
        return defaultUserFacade.getUserById(id);
    }
}
