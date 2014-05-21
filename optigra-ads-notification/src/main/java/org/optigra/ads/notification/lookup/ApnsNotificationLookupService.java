package org.optigra.ads.notification.lookup;

import java.io.InputStream;

import org.optigra.ads.apns.model.device.ApnsNotifiableDevice;
import org.optigra.ads.model.certificate.Certificate;
import org.optigra.ads.notification.service.ApnsDeviceNotificationService;
import org.optigra.ads.notification.service.DeviceNotificationService;
import org.springframework.stereotype.Component;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

@Component("apnsNotificationLookupService")
public class ApnsNotificationLookupService implements DeviceNotificationLookupService<ApnsNotifiableDevice> {

	@Override
	public DeviceNotificationService<ApnsNotifiableDevice> lookup(InputStream certificateStream, Certificate certificate) {
		
		ApnsService apnsService = APNS.newService().withCert(certificateStream, certificate.getPassword()).withSandboxDestination().build();
		
		ApnsDeviceNotificationService notificationService = new ApnsDeviceNotificationService();
		notificationService.setApnsService(apnsService);
		
		return notificationService;
	}

}
