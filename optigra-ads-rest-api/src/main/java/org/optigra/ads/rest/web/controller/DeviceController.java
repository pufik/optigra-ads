package org.optigra.ads.rest.web.controller;

import javax.annotation.Resource;

import org.optigra.ads.facade.device.DeviceFacade;
import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.device.DeviceResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = ResourceUri.DEVICE)
public class DeviceController extends BaseController {

	@Resource(name = "deviceFacade")
	private DeviceFacade deviceFacade;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public MessageResource createDevice(@RequestBody DeviceResource deviceResource) {
		deviceFacade.createDevice(deviceResource);
		return new MessageResource(MessageType.INFO, "Device created");
	}

	@RequestMapping(value = ResourceUri.ID, method = RequestMethod.PUT)
	@ResponseBody
	public MessageResource updateDevice(@PathVariable("id") String deviceUid, @RequestBody DeviceResource deviceResource) {
		deviceFacade.updateDevice(deviceUid, deviceResource);
		return new MessageResource(MessageType.INFO, "Device updated");
	}

	@RequestMapping(value = ResourceUri.ID, method = RequestMethod.GET)
	@ResponseBody
	public DeviceResource getDevice(@PathVariable("id") String deviceUid) {
		return deviceFacade.getDevice(deviceUid);
	}

	@RequestMapping(value = ResourceUri.ID, method = RequestMethod.DELETE)
	@ResponseBody
	public MessageResource deleteDevice(@PathVariable("id") String deviceUid) {
		deviceFacade.deleteDevice(deviceUid);
		return new MessageResource(MessageType.INFO, "Device deleted");
	}
	
}
