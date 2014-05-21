package org.optigra.ads.notification.lookup;

import java.io.InputStream;

import org.optigra.ads.apns.model.device.Device;
import org.optigra.ads.model.certificate.Certificate;
import org.optigra.ads.notification.service.DeviceNotificationService;

public interface DeviceNotificationLookupService<T extends Device> {

	DeviceNotificationService<T> lookup(InputStream certificateStream, Certificate certificate);
	
}
