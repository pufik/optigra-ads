package org.optigra.ads.rest.web.controller;

import javax.annotation.Resource;

import org.optigra.ads.facade.advertising.AdvertisingFacade;
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for handling requests for advertising entities.
 *
 * @author Iurii Parfeniuk
 */
@Controller
@RequestMapping(value = ResourceUri.ADVERTISING)
public class AdvertisingController extends BaseController {

    @Resource(name = "advertisingFacade")
    private AdvertisingFacade advertisingFacade;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public PagedResultResource<AdvertisingResource> getAdvertisings(@RequestParam(value = "offset", defaultValue = "0") final int offset,
            @RequestParam(value = "limit", defaultValue = "20") final int limit) {

        return advertisingFacade.getAdvertisings(offset, limit);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public MessageResource createAdvertising(@RequestBody final AdvertisingResource resource) {
        
        advertisingFacade.createAdvertising(resource);
        
        return new MessageResource(MessageType.INFO, "Advertising created");
    }
}
