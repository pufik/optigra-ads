package org.optigra.ads.service.notification;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.notification.executor.NotificationBatchExecutor;

@RunWith(MockitoJUnitRunner.class)
public class DefaultNotificationServiceTest {

	@Mock
	private NotificationBatchExecutor notificationBatchExecutor;
	
	@InjectMocks
	private DefaultNotificationService unit = new DefaultNotificationService();
	
	@Test
	public void testSend() throws Exception {
		// Given
		String message = "Message";
		String title = "title";
		
		String applicationId = "ApplicationId";

		Application application = new Application();
		application.setApplicationId(applicationId);
		
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setTitle(title);

		// When
		unit.send(application, notification);

		// Then
		verify(notificationBatchExecutor).process(application, notification);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSendSingleDevice() throws Exception {
		// Given
		String message = "Message";
		String title = "title";
		
		String deviceUid = "deviceUid";
		Device device = new Device();
		device.setDeviceUid(deviceUid);
		
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setTitle(title);
		
		// When
		unit.send(device, notification);
	}
	
}
