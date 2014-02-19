package org.optigra.ads.rest.web.controller;

import java.io.ByteArrayOutputStream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.optigra.ads.configuration.ConfigurationService;
import org.optigra.ads.facade.resource.ApiDetailsResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.springframework.stereotype.Controller;
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

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @Resource
    private ServletContext servletContext;

    private static final ApiDetailsResource API_DETAILS = new ApiDetailsResource();

    private String wadlHtml = null;
    private static final String CONTEXT_PATH_VAR = "providedBase";
    private static final String APPLICATION_API_PROPERTY = "application.api.context.path";
    private static final String WADL_XSL = "WEB-INF/wadl/wadl.xsl";
    private static final String WADL_XML = "WEB-INF/wadl/application.wadl.xml";

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

    private String getApiContextPath() {
        return configurationService.getProperty(APPLICATION_API_PROPERTY);
    }
}
