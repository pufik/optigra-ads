package org.optigra.ads.rest.web;

import org.optigra.ads.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Base Controller.
 *
 * @author Ivan Ursul
 */
@Controller
public class BaseController {

    /**
     * Temporary end-point.
     *
     * @return "Hello World"
     */
    @RequestMapping(value = "/")
    @ResponseBody
    public User example(@RequestParam(value = "userId", defaultValue = "10") final Long userId) {
        User user = new User();
        user.setId(userId);

        return user;
    }
}
