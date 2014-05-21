package org.optigra.ads.notification.reader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.pagination.BaseSearch;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.service.device.DeviceService;

@RunWith(MockitoJUnitRunner.class)
public class DeviceItemReaderTest {

	@Mock
	private DeviceService deviceService;
	
	@InjectMocks
	private DeviceItemReader unit = new DeviceItemReader();
	
	@Test
	public void testGetItems() throws Exception {
		// Given
		String applicationId = "appId";
		Application application = new Application();
		application.setApplicationId(applicationId);

		int start = 0;
		int limit = 20;
		long count = 100;
		BaseSearch search = new BaseSearch(start, limit);
		
		Device device1 = new Device();
		List<Device> entities = Arrays.asList(device1);
		PagedResult<Device> expected = new PagedResult<Device>(start, limit, count, entities);
		
		// When
		when(deviceService.getApplicationDevices(anyString(), anyInt(), anyInt())).thenReturn(expected);
		PagedResult<Device> actual = unit.getItems(application, search);

		// Then
		verify(deviceService).getApplicationDevices(applicationId, start, limit);
		assertEquals(expected, actual);
	}
}
