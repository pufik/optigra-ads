package org.optigra.ads.rest.web.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.optigra.ads.configuration.ConfigurationService;
import org.optigra.ads.facade.resource.user.UserResource;
import org.optigra.ads.facade.user.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/authorize")
public class AuthController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    protected static final String MY_PABLICI_HOME_URL = "my.pablici.home.url";

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public UserResource authorizeByLoginAndPassword(@RequestParam(value = "login") final String login, @RequestParam(value = "password") final String password ) throws IOException {

        logger.info("Authorize user : {}", login);

        return userFacade.authorizeUserByLoginAndPassword(login, password);
    }

    @RequestMapping(value = "/vkontakte", method = RequestMethod.GET)
    public String vkAuthorizeByCode(@RequestParam(value = "code") final String code) throws IOException {

        logger.info("Vkontakte service provider code: {}", code);

        userFacade.authorizeSocialUserByCode(code);

        return REDIRECT + configurationService.getProperty(MY_PABLICI_HOME_URL);
    }

    @RequestMapping(value = "/vkontakte/{token}", method = RequestMethod.GET)
    @ResponseBody
    public UserResource vkAuthorizeByToken(@PathVariable(value = "token") final String token) throws IOException {

        logger.info("Vkontakte service provider token: {}", token);

        return userFacade.authorizeSocialUserByToken(token);
    }
}
