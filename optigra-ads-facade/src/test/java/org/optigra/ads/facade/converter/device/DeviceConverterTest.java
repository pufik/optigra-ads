package org.optigra.ads.facade.converter.device;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.facade.resource.device.DeviceResource;
import org.optigra.ads.model.device.Device;

public class DeviceConverterTest {

	private DeviceConverter unit = new DeviceConverter();
	
	@Test
	public void testConvert() throws Exception {
		// Given
		Long id = 1L;
		String deviceToken = "deviceToken";
		String deviceUid = "deviceUid";
		
		Device source = new Device();
		source.setDeviceToken(deviceToken);
		source.setDeviceUid(deviceUid);
		source.setId(id);
		
		DeviceResource expected = new DeviceResource();
		expected.setDeviceToken(deviceToken);
		expected.setDeviceUid(deviceUid);
		expected.setId(id);
		
		// When
		DeviceResource actual = unit.convert(source);

		// Then
		assertEquals(expected, actual);
	}
}
