package org.optigra.ads.rest.web.controller;

import static org.optigra.ads.rest.web.util.Constants.APPLICATION_API_PROPERTY;
import static org.optigra.ads.rest.web.util.Constants.JAVAX_SERVLET_ERROR_EXCEPTION;
import static org.optigra.ads.rest.web.util.Constants.JAVAX_SERVLET_ERROR_MESSAGE;
import static org.optigra.ads.rest.web.util.Constants.JAVAX_SERVLET_ERROR_STATUS_CODE;

import java.io.ByteArrayOutputStream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.optigra.ads.configuration.ConfigurationService;
import org.optigra.ads.facade.resource.ApiDetailsResource;
import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.ResourceUri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for system stuff.
 *
 * @author Iurii Parfeniuk
 */

@Controller
public class SystemController {

    private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    private static final String CONTEXT_PATH_VAR = "providedBase";
    private static final String WADL_XSL = "WEB-INF/wadl/wadl.xsl";
    private static final String WADL_XML = "WEB-INF/wadl/application.wadl.xml";

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @Resource
    private ServletContext servletContext;

    private static final ApiDetailsResource API_DETAILS = new ApiDetailsResource();

    private String wadlHtml = null;

    @PostConstruct
    public void init() throws TransformerException {
        ByteArrayOutputStream writer = new ByteArrayOutputStream();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Source xsltSource = new StreamSource(servletContext.getResourceAsStream(WADL_XSL));
        Source xmlSource = new StreamSource(servletContext.getResourceAsStream(WADL_XML));

        Transformer transformer = transformerFactory.newTransformer(xsltSource);
        transformer.setParameter(CONTEXT_PATH_VAR, getApiContextPath());

        Result outputTarget = new StreamResult(writer);
        transformer.transform(xmlSource, outputTarget);

        wadlHtml = writer.toString();
    }


    public static final String AUTHENTICATION_HEADER = "WWW-Authenticate";

    /**
     * Application WADL
     *
     * @date Feb 18, 2014
     * @author iurii
     * @return
     */
    @RequestMapping(value = ResourceUri.WADL)
    @ResponseBody
    public String getWadl() {
        return wadlHtml;
    }

    /**
     * Method returns REST API details.
     *
     * @date Jan 24, 2014
     * @author Iurii Parfeniuk
     * @return details about REST API {@link ApiDetailsResource}
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ApiDetailsResource getApiDetails() {
        return API_DETAILS;
    }

    /**
     * Servlet Error's handler
     *
     * @date Jun 16, 2014
     * @author Iurii Parfeniuk
     * @param errorCode
     *            - code of Http status
     * @param request
     *            - {@link HttpServletRequest}
     * @return {@link ResponseEntity}
     */

    @RequestMapping(value = "/error/{errorCode}")
    @ResponseBody
    public ResponseEntity<MessageResource> handleException(@PathVariable(value = "errorCode") final Integer errorCode, final HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(JAVAX_SERVLET_ERROR_STATUS_CODE);
        String exceptionMessage = (String) request.getAttribute(JAVAX_SERVLET_ERROR_MESSAGE);
        Throwable exception = (Throwable) request.getAttribute(JAVAX_SERVLET_ERROR_EXCEPTION);

        logger.error("Handle exception from Servlet container: status code - " + statusCode, exception);

        int responseCode = (errorCode == null) ? statusCode : errorCode;

        // TODO: Change to internal codes. Don't return exception message from Exception object.
        MessageResource message = new MessageResource(MessageType.ERROR, Long.valueOf(responseCode), exceptionMessage);

        return new ResponseEntity<MessageResource>(message, HttpStatus.valueOf(statusCode));
    }

    private String getApiContextPath() {
        return configurationService.getProperty(APPLICATION_API_PROPERTY);
    }
}
