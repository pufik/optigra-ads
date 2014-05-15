package org.optigra.ads.notification.converter;

import org.optigra.ads.apns.model.device.ApnsNotifiableDevice;
import org.optigra.ads.model.device.Device;
import org.springframework.stereotype.Component;

@Component("apnsDeviceConverter")
public class ApnsDeviceConverter implements Converter<Device, ApnsNotifiableDevice> {

	@Override
	public ApnsNotifiableDevice convert(Device source) {
		
		ApnsNotifiableDevice target = new ApnsNotifiableDevice();
		target.setDeviceToken(source.getDeviceToken());
		
		return target;
	}

}
