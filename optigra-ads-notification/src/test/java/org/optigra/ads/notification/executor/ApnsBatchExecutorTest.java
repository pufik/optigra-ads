package org.optigra.ads.notification.executor;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.pagination.BaseSearch;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.notification.processor.ItemProcessor;
import org.optigra.ads.notification.reader.ItemReader;

@RunWith(MockitoJUnitRunner.class)
public class ApnsBatchExecutorTest {

	@Mock
	private ItemReader<Application, Device> itemReader;
	
	@Mock
	private ItemProcessor itemProcessor;
	
	@InjectMocks
	private ApnsBatchExecutor unit = new ApnsBatchExecutor();
	
	private Integer limit;

	@Before
	public void setup() {
		limit = 20;
		unit.setLimit(limit);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testProcess() throws Exception {
		// Given
		int start = 0;
		String message = "message";
		String title = "title";
		long count = 100;
		
		String applicationId = "applicationId";

		Application application = new Application();
		application.setApplicationId(applicationId);

		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setTitle(title);
		
		Device device1 = new Device();
		List<Device> entities = Arrays.asList(device1);
		PagedResult<Device> pagedResult = new PagedResult<Device>(start, limit, count, entities);
		
		// When
		when(itemReader.getItems(any(Application.class), any(BaseSearch.class))).thenReturn(pagedResult);
		unit.process(application, notification);

		// Then
		int loopCount = (int) (count / limit);
		verify(itemProcessor, times(loopCount)).process(anyListOf(Device.class), any(Notification.class));
	}

	@Test
	public void testProcessWithAnotherCount() throws Exception {
		// Given
		int start = 0;
		String message = "message";
		String title = "title";
		long count = 169;
		
		String applicationId = "applicationId";
		
		Application application = new Application();
		application.setApplicationId(applicationId);
		
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setTitle(title);
		
		Device device1 = new Device();
		List<Device> entities = Arrays.asList(device1);
		PagedResult<Device> pagedResult = new PagedResult<Device>(start, limit, count, entities);
		
		// When
		when(itemReader.getItems(any(Application.class), any(BaseSearch.class))).thenReturn(pagedResult);
		unit.process(application, notification);
		
		// Then
		int loopCount = (int) BigDecimal.valueOf(count).divide(BigDecimal.valueOf(limit), RoundingMode.UP).longValue();
		verify(itemProcessor, times(loopCount)).process(anyListOf(Device.class), any(Notification.class));
	}
}
