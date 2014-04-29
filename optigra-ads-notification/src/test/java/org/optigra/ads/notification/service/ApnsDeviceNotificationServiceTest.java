package org.optigra.ads.notification.service;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.apns.model.device.ApnsNotifiableDevice;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

@RunWith(MockitoJUnitRunner.class)
public class ApnsDeviceNotificationServiceTest {

	@Mock
	private ApnsService apnsServiceMock;
	
	@InjectMocks
	private ApnsDeviceNotificationService unit = new ApnsDeviceNotificationService();
	
	@Test
	public void testNotify() throws Exception {
		
		String message = "someMessage";
		
		ApnsNotifiableDevice device = new ApnsNotifiableDevice();
		
		String deviceToken = "ad78e987f9d0a09c";
		device.setDeviceToken(deviceToken);
		
		unit.notify(device, message);
		
		verify(apnsServiceMock).push(deviceToken, APNS.newPayload().alertBody(message).build());
	}
}
