package org.optigra.ads.notification.processor;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.apns.model.device.ApnsNotifiableDevice;
import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.certificate.Certificate;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.notification.converter.Converter;
import org.optigra.ads.notification.lookup.DeviceNotificationLookupService;
import org.optigra.ads.notification.service.DeviceNotificationService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultItemProcessorTest {

	@Mock
	private DeviceNotificationLookupService<ApnsNotifiableDevice> notificationLookupService;
	
	@Mock
	private Converter<Notification, String> notificationConverter;

	@Mock
	private Converter<Device, ApnsNotifiableDevice> deviceConverter;
	
	@Mock
	private DeviceNotificationService<ApnsNotifiableDevice> notificationService;
	
	@Mock
	private InputStream certificateStream;

	@InjectMocks
	private DefaultItemProcessor unit = new DefaultItemProcessor();

	@Test
	public void testProcess() throws Exception {
		// Given
		String message = "some message";
		String deviceToken = "deviceToken";
		String path = "path";

		Device device1 = new Device();
		device1.setDeviceToken(deviceToken);

		List<Device> entities = Arrays.asList(device1);
		Notification notification = new Notification();
		Application application = new Application();
		
		ApnsNotifiableDevice apnsDevice = new ApnsNotifiableDevice();
		apnsDevice.setDeviceToken(deviceToken);

		Certificate certificate = new Certificate();
		certificate.setApplication(application);
		certificate.setPath(path);
		
		// When
		when(notificationConverter.convert(any(Notification.class))).thenReturn(message);
		when(deviceConverter.convert(any(Device.class))).thenReturn(apnsDevice);
		
		
		unit.process(notificationService, entities, notification);

		// Then
		verify(deviceConverter).convert(device1);
		verify(notificationService).notify(apnsDevice, message);
	}
}
