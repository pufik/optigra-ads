package org.optigra.ads.service.notification;

import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.device.Device;

public interface NotificationService {

	/**
	 * Method for sending apns for devices from all applications
	 * @param application
	 * @param notification
	 */
	void send(Application application, Notification notification);
	
	/**
	 * Method for sending apns for single device
	 * @param device
	 * @param notification
	 */
	void send(Device device, Notification notification);
	
}
