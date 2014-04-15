package org.optigra.ads.rest.web.controller;

import javax.annotation.Resource;

import org.optigra.ads.facade.advertising.AdvertisingFacade;
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = ResourceUri.ADVERTISING_BY_ID, method = RequestMethod.GET)
    @ResponseBody
    public AdvertisingResource getAdvertising(@PathVariable("advertisingId") Long advertisingId) {
    	return advertisingFacade.getAdvertising(advertisingId);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AdvertisingResource createAdvertising(@RequestBody final AdvertisingResource resource) {
        return advertisingFacade.createAdvertising(resource);
    }

    @RequestMapping(value = ResourceUri.ADVERTISING_BY_ID,method = RequestMethod.PUT)
    @ResponseBody
    public MessageResource updateAdvertising(@PathVariable("advertisingId") Long advertisingId,
    		@RequestBody final AdvertisingResource resource) {
    	advertisingFacade.updateAdvertising(advertisingId, resource);
    	return new MessageResource(MessageType.INFO, "Advertising updated");
    }

    @RequestMapping(value = ResourceUri.ADVERTISING_BY_ID,method = RequestMethod.DELETE)
    @ResponseBody
    public MessageResource deleteAdvertising(@PathVariable("advertisingId") Long advertisingId) {
    	advertisingFacade.deleteAdvertising(advertisingId);
    	return new MessageResource(MessageType.INFO, "Advertising deleted");
    }
}
