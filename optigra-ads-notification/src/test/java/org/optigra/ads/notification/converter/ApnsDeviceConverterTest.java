package org.optigra.ads.notification.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.apns.model.device.ApnsNotifiableDevice;
import org.optigra.ads.model.device.Device;

public class ApnsDeviceConverterTest {

	private ApnsDeviceConverter unit = new ApnsDeviceConverter();
	
	@Test
	public void testConvert() throws Exception {
		// Given
		String deviceToken = "deviceToken";
		Device source = new Device();
		source.setDeviceToken(deviceToken);

		ApnsNotifiableDevice expected = new ApnsNotifiableDevice();
		expected.setDeviceToken(deviceToken);
		
		// When
		ApnsNotifiableDevice actual = unit.convert(source);

		// Then
		assertEquals(expected, actual);
	}
}
