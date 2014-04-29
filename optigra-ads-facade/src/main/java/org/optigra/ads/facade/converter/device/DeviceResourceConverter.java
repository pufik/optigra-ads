package org.optigra.ads.facade.converter.device;

import java.util.Date;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.device.DeviceResource;
import org.optigra.ads.model.device.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceResourceConverter extends AbstractConverter<DeviceResource, Device> {

	@Override
	public Device convert(DeviceResource source, Device target) {
		
		target.setUpdateDate(new Date());
		target.setDeviceToken(source.getDeviceToken());
		target.setDeviceUid(source.getDeviceUid());
		
		return target;
	}

	@Override
	public Device convert(DeviceResource source) {
		return convert(source, new Device());
	}

}
