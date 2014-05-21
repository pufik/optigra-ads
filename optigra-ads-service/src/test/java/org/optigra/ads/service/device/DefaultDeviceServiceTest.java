package org.optigra.ads.service.device;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
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
import org.optigra.ads.dao.application.ApplicationDao;
import org.optigra.ads.dao.device.DeviceDao;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDeviceServiceTest {

    @Mock
    private DeviceDao deviceDao;
    
    @Mock
	private ApplicationDao applicationDao;

    @InjectMocks
    private final DefaultDeviceService unit = new DefaultDeviceService();

    @Test
    public void testCreateDevice() throws Exception {
        // Given
        String deviceToken = "device token";
        String deviceUid = "device uid";
        Device device = new Device();
        device.setDeviceToken(deviceToken);
        device.setDeviceUid(deviceUid);

        // When
        unit.createDevice(device);

        // Then
        verify(deviceDao).create(device);
    }

    @Test
    public void testGetDeviceByUid() throws Exception {
        // Given
        String deviceToken = "device token";
        String deviceUid = "device uid";
        Device expected = new Device();
        expected.setDeviceToken(deviceToken);
        expected.setDeviceUid(deviceUid);

        // When
        when(deviceDao.getDeviceByUid(anyString())).thenReturn(expected);

        Device actual = unit.getDeviceByUid(deviceUid);

        // Then
        verify(deviceDao).getDeviceByUid(deviceUid);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDeviceByUidAndApplicationId() throws Exception {
        // Given
        String deviceToken = "device token";
        String deviceUid = "device uid";
        String applicationId = "appId";
        Device expected = new Device();
        expected.setDeviceToken(deviceToken);
        expected.setDeviceUid(deviceUid);

        when(deviceDao.getDeviceByUidAndAplication(anyString(), anyString())).thenReturn(expected);

        // When
        Device actual = unit.getDeviceByUidAndApplicationId(deviceUid, applicationId);

        // Then
        verify(deviceDao).getDeviceByUidAndAplication(deviceUid, applicationId);
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateDevice() throws Exception {
        // Given
        String deviceToken = "device token";
        String deviceUid = "device uid";
        Device device = new Device();
        device.setDeviceToken(deviceToken);
        device.setDeviceUid(deviceUid);

        // When
        unit.updateDevice(device);

        // Then
        verify(deviceDao).update(device);
    }

    @Test
    public void testDeleteDevice() throws Exception {
        // Given
        String deviceUid = "deviceUid";
        String deviceToken = "deviceToken";
        User owner = new User();

        Device device = new Device();
        device.setDeviceToken(deviceToken);
        device.setDeviceUid(deviceUid);
        device.setOwner(owner);

        // When
        when(deviceDao.getDeviceByUid(anyString())).thenReturn(device);
        unit.deleteDevice(deviceUid);

        // Then
        verify(deviceDao).getDeviceByUid(deviceUid);
        verify(deviceDao).remove(device);
    }
    
    @Test
	public void testApplicationDevices() throws Exception {
		// Given
    	String applicationId = "applicationId";
    	int start = 10;
    	int limit = 20;
    	long count = 100;
    	Device entity1 = new Device();
		List<Device> entities = Arrays.asList(entity1);

		PagedResult<Device> expected = new PagedResult<Device>(start, limit, count, entities);
    	
		Application application = new Application();
		application.setApplicationId(applicationId);
		
		// When
		when(applicationDao.getApplicationById(anyString())).thenReturn(application);
		when(deviceDao.getApplicationDevices(any(Application.class), anyInt(), anyInt())).thenReturn(expected);
		
    	PagedResult<Device> actual = unit.getApplicationDevices(applicationId, start, limit);

		// Then
    	verify(applicationDao).getApplicationById(applicationId);
    	verify(deviceDao).getApplicationDevices(application, start, limit);
    	assertEquals(expected, actual);
	}
}
