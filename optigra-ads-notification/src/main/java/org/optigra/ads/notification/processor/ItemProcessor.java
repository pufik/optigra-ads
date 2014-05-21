package org.optigra.ads.notification.processor;

import java.util.List;

import org.optigra.ads.apns.model.device.ApnsNotifiableDevice;
import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.notification.service.DeviceNotificationService;

/**
 * 
 * Processor, that is responsible for processing notification
 * 
 * @author ivanursul
 *
 */
public interface ItemProcessor {

	/**
	 * method for processing items
	 * @param entities
	 */
	void process(DeviceNotificationService<ApnsNotifiableDevice> apnsNotificationService, List<Device> entities, Notification notification);

}
