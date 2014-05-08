package org.optigra.ads.dao.device;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.persistence.PersistenceManager;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.query.Queries;
import org.optigra.ads.model.query.Query;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDeviceDaoTest {

	@Mock
    private PersistenceManager<Device, Long> persistenceManager;

	@InjectMocks
	private final DefaultDeviceDao unit = new DefaultDeviceDao();

	@Test
	public void testGetDeviceByUid() throws Exception {
		// Given
		String deviceUid = "device uid";

		Device expected = new Device();
		Queries queries = Queries.FIND_DEVICE_BY_UID;
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("deviceUid", deviceUid);

		Query<Device> jpQuery = new Query<Device>(unit.getEntityClass(), queries.getQuery(), parameters );

		// When
		when(persistenceManager.executeQuerySingleResult(Matchers.<Query<Device>>any())).thenReturn(expected);
		Device actual = unit.getDeviceByUid(deviceUid);

		// Then
		verify(persistenceManager).executeQuerySingleResult(jpQuery);
		assertEquals(expected, actual);
	}

	@Test
    public void testGetDeviceByUidAndApplication() throws Exception {
        // Given
        String deviceUid = "device uid";
        String applicationId = "app";

        Device expected = new Device();
        Queries queries = Queries.FIND_DEVICE_BY_UID_AND_APPLICATION;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("deviceUid", deviceUid);
        parameters.put("applicationId", applicationId);

        Query<Device> jpQuery = new Query<Device>(unit.getEntityClass(), queries.getQuery(), parameters );

        // When
        when(persistenceManager.executeQuerySingleResult(Matchers.<Query<Device>>any())).thenReturn(expected);
        Device actual = unit.getDeviceByUidAndAplication(deviceUid, applicationId);

        // Then
        verify(persistenceManager).executeQuerySingleResult(jpQuery);
        assertEquals(expected, actual);
    }
}
