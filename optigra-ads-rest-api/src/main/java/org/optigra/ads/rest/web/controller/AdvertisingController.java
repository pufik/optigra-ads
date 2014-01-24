package org.optigra.ads.rest.web.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.optigra.ads.facade.dto.AdvertisingResource;
import org.optigra.ads.facade.dto.ResourceUri;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for handling requests for advertising entities.
 *
 * @author Iurii Parfeniuk
 */
@Controller
@RequestMapping(value = ResourceUri.ADVERTISING)
public class AdvertisingController extends BaseController {
    // TODO: IP - Temporary stuff. Should be removed
    private static final String API_CONTENT_ADVERTISING_LOGO = "/api/content/advertising/logo/";
    private static final String API_CONTENT_ADVERTISING = "/api/content/advertising/";
    private static final String HTTP_COMPANY_COM = "http://company.com";
    private static final String ADVERTISING_DESCRIPTION = "Advertising description";
    private static final String SIMPLE_ADVERTISING_EXAMPLE = "Simple advertising example";

    /**
     * Default search handling.
     *
     * @return list of advertising {@link AdvertisingResource}
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<AdvertisingResource> search() {
        AdvertisingResource resource = new AdvertisingResource();

        resource.setUid(BigDecimal.ZERO.longValue());
        resource.setTitle(SIMPLE_ADVERTISING_EXAMPLE);
        resource.setDescription(ADVERTISING_DESCRIPTION);
        resource.setDescription(HTTP_COMPANY_COM);
        resource.setImageUrl(API_CONTENT_ADVERTISING + resource.getUid());
        resource.setLogoUrl(API_CONTENT_ADVERTISING_LOGO + resource.getUid());

        return Arrays.asList(resource);
    }
}
