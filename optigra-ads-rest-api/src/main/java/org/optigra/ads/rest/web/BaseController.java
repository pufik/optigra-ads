package org.optigra.ads.rest.web;

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

    private static final String HELLO_WORLD = "Hello World";

    /**
     * Temporary end-point.
     *
     * @return "Hello World"
     */
    @RequestMapping(value = "/")
    @ResponseBody
    public String example() {
        return HELLO_WORLD;
    }
}
