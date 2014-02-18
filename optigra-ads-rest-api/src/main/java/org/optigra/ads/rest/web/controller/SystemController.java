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

    private static final String WADL_XSL = "WEB-INF/wadl/wadl.xsl";
    private static final String WADL_XML = "WEB-INF/wadl/application.wadl.xml";

    private static final ApiDetailsResource API_DETAILS = new ApiDetailsResource();

    private String wadlHtml = "Hello world!!!";

    @Resource
    private ServletContext servletContext;

    @PostConstruct
    public void init() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Source xsltSource = new StreamSource(servletContext.getResourceAsStream(WADL_XSL));

        Transformer transformer = transformerFactory.newTransformer(xsltSource);
        Source xmlSource = new StreamSource(servletContext.getResourceAsStream(WADL_XML));

        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        Result outputTarget = new StreamResult(writer);
        transformer.transform(xmlSource, outputTarget);

        wadlHtml = writer.toString();
    }

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
}
