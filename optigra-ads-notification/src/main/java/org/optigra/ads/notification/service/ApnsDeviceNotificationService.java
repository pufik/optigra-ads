package org.optigra.ads.notification.service;

import javax.annotation.Resource;

import org.optigra.ads.model.device.ApnsNotifiableDevice;
import org.springframework.stereotype.Service;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

@Service("apnsDeviceNotificationService")
public class ApnsDeviceNotificationService implements DeviceNotificationService<ApnsNotifiableDevice> {

    @Resource(name = "apnsServiceFactoryBean")
	private ApnsService apnsService;
	
	public void notify(ApnsNotifiableDevice device, String message) {
		String payload = APNS.newPayload().alertBody(message).build();
		apnsService.push(device.getDeviceToken(), payload);
	}
	
	public void setApnsService(ApnsService apnsService) {
		this.apnsService = apnsService;
	}
}
