package org.optigra.ads.rest.web.controller;

import javax.annotation.Resource;

import org.optigra.ads.facade.device.DeviceFacade;
import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.device.DeviceResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = ResourceUri.DEVICE)
public class DeviceController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceController.class);

    @Resource(name = "deviceFacade")
    private DeviceFacade deviceFacade;

    @RequestMapping(value = ResourceUri.ID, method = RequestMethod.GET)
    @ResponseBody
    public DeviceResource getDevice(@PathVariable("id") final String deviceUid) {
        return deviceFacade.getDevice(deviceUid);
    }

    @RequestMapping(value = ResourceUri.DEVICE_APPLICATION, method = RequestMethod.GET)
    @ResponseBody
    public DeviceResource getDeviceByApplication(@PathVariable("deviceId") final String deviceUid, @PathVariable("applicationId") final String applicationId) {
        LOG.info(String.format("Get device information for device UID: [%s] and Application UID: [%s]", deviceUid, applicationId));

        return deviceFacade.getDeviceByUidAndApplication(deviceUid, applicationId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public MessageResource createDevice(@RequestBody final DeviceResource deviceResource) {
        deviceFacade.createDevice(deviceResource);

        return new MessageResource(MessageType.INFO, "Device created");
    }

    @RequestMapping(value = ResourceUri.ID, method = RequestMethod.PUT)
    @ResponseBody
    public MessageResource updateDevice(@PathVariable("id") final String deviceUid, @RequestBody final DeviceResource deviceResource) {
        deviceFacade.updateDevice(deviceUid, deviceResource);

        return new MessageResource(MessageType.INFO, "Device updated");
    }

    @RequestMapping(value = ResourceUri.ID, method = RequestMethod.DELETE)
    @ResponseBody
    public MessageResource deleteDevice(@PathVariable("id") final String deviceUid) {
        deviceFacade.deleteDevice(deviceUid);

        return new MessageResource(MessageType.INFO, "Device deleted");
    }
}