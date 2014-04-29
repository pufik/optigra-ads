package org.optigra.ads.facade.converter.device;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.device.DeviceResource;
import org.optigra.ads.model.device.Device;
import org.springframework.stereotype.Component;

@Component("deviceConverter")
public class DeviceConverter extends AbstractConverter<Device, DeviceResource> {

	@Override
	public DeviceResource convert(Device source, DeviceResource target) {
		
		target.setId(source.getId());
		target.setDeviceToken(source.getDeviceToken());
		target.setDeviceUid(source.getDeviceUid());
		
		return target;
	}

	@Override
	public DeviceResource convert(Device source) {
		return convert(source, new DeviceResource());
	}

}
