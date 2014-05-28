package org.optigra.ads.facade.converter.device;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.facade.resource.device.DeviceResource;
import org.optigra.ads.model.device.Device;

public class DeviceResourceConverterTest {

	private final DeviceResourceConverter unit = new DeviceResourceConverter();

	@Test
	public void testConvert() throws Exception {
		// Given
		String deviceToken = "device token";
		String deviceUid = "device uid";

		DeviceResource source =  new DeviceResource();
		source.setDeviceToken(deviceToken);
		source.setDeviceUid(deviceUid);

		Device expected = new Device();
		expected.setDeviceToken(deviceToken);
		expected.setDeviceUid(deviceUid);

		// When
		Device actual = unit.convert(source);
		actual.setUpdateDate(null);

		// Then
		assertEquals(expected, actual);
	}
}
