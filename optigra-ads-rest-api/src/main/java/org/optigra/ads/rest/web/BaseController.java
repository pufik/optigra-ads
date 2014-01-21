package org.optigra.ads.rest.web;

import org.optigra.ads.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Base Controller.
 *
 * @author Ivan Ursul
 */
@Controller
public class BaseController {

    private static final long id = 10L;

	/**
     * Temporary end-point.
     *
     * @return "Hello World"
     */
    @RequestMapping(value = "/")
    @ResponseBody
    public User example() {
    	User user = new User();
		user.setId(id);
        return user;
    }
}
