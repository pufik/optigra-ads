package org.optigra.ads.notification.service;

import org.optigra.ads.model.device.Device;

public interface DeviceNotificationService<T extends Device> {
	
	void notify(T device, String message);
}
