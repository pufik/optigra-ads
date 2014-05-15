package org.optigra.ads.notification.processor;

import java.util.List;

import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.model.device.Device;

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
	void process(List<Device> entities, Notification notification);

}
