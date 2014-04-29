package org.optigra.ads.facade.device;

import java.util.Date;

import javax.annotation.Resource;

import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.device.DeviceResource;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.security.session.SessionService;
import org.optigra.ads.service.device.DeviceService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("deviceFacade")
@Transactional
public class DefaultDeviceFacade implements DeviceFacade {

	@Resource(name = "deviceResourceConverter")
	private Converter<DeviceResource, Device> deviceResourceConverter;
	
	@Resource(name = "deviceConverter")
	private Converter<Device, DeviceResource> deviceConverter;
	
	@Resource(name = "deviceService")
	private DeviceService deviceService;
	
    @Resource(name = "sessionService")
    private SessionService sessionService;
	
	@Override
	public void createDevice(DeviceResource deviceResource) {
		Device device = deviceResourceConverter.convert(deviceResource);
		device.setOwner(sessionService.getCurrentSession().getUser());
		device.setCreateDate(new Date());
		
		deviceService.createDevice(device);
	}

	@Override
	public void updateDevice(String deviceUid, DeviceResource deviceResource) {
		Device device = deviceService.getDeviceByUid(deviceUid);
		deviceResourceConverter.convert(deviceResource, device);
		deviceService.updateDevice(device);
	}

	@Override
	public void deleteDevice(String deviceUid) {
		deviceService.deleteDevice(deviceUid);
	}

	@Override
	public DeviceResource getDevice(String deviceUid) {
		Device device = deviceService.getDeviceByUid(deviceUid);
		DeviceResource deviceResource = deviceConverter.convert(device);
		return deviceResource;
	}

}
