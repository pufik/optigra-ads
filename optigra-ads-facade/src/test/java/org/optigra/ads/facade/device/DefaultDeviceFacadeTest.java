package org.optigra.ads.facade.device;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.device.DeviceResource;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.user.User;
import org.optigra.ads.security.session.Session;
import org.optigra.ads.security.session.SessionService;
import org.optigra.ads.service.device.DeviceService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDeviceFacadeTest {

    @Mock
    private Converter<DeviceResource, Device> deviceResourceConverter;

    @Mock
    private Converter<Device, DeviceResource> deviceConverter;

    @Mock
    private DeviceService deviceService;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private final DefaultDeviceFacade unit = new DefaultDeviceFacade();

    @Test
    public void testCreateDevice() throws Exception {
        // Given
        String deviceToken = "device token";
        String deviceUid = "new device token";

        DeviceResource deviceResource = new DeviceResource();
        deviceResource.setDeviceToken(deviceToken);
        deviceResource.setDeviceUid(deviceUid);

        User owner = new User();
        Date createDate = Calendar.getInstance().getTime();
        Date updateDate = Calendar.getInstance().getTime();

        Device device = new Device();
        device.setCreateDate(createDate);
        device.setDeviceToken(deviceToken);
        device.setDeviceUid(deviceUid);
        device.setOwner(owner);
        device.setUpdateDate(updateDate);

        // When
        when(sessionService.getCurrentSession()).thenReturn(new Session(owner));
        when(deviceResourceConverter.convert(any(DeviceResource.class))).thenReturn(device);

        unit.createDevice(deviceResource);

        // Then
        verify(deviceService).createDevice(device);
    }

    @Test
    public void testUpdateDevice() throws Exception {
        // Given
        String deviceToken = "device token";
        String deviceUid = "new device token";

        DeviceResource deviceResource = new DeviceResource();
        deviceResource.setDeviceToken(deviceToken);
        deviceResource.setDeviceUid(deviceUid);

        User owner = new User();
        Date createDate = Calendar.getInstance().getTime();
        Date updateDate = Calendar.getInstance().getTime();

        Device device = new Device();
        device.setCreateDate(createDate);
        device.setDeviceToken(deviceToken);
        device.setDeviceUid(deviceUid);
        device.setOwner(owner);
        device.setUpdateDate(updateDate);

        // When
        when(deviceService.getDeviceByUid(anyString())).thenReturn(device);

        unit.updateDevice(deviceUid, deviceResource);

        // Then
        verify(deviceResourceConverter).convert(deviceResource, device);
        verify(deviceService).updateDevice(device);
    }

    @Test
    public void testDeleteDevice() throws Exception {
        // Given
        String deviceUid = "deviceUid";

        // When
        unit.deleteDevice(deviceUid);

        // Then
        verify(deviceService).deleteDevice(deviceUid);
    }

    @Test
    public void testGetDevice() throws Exception {
        // Given
        String deviceUid = "deviceUid";
        String deviceToken = "deviceToken";

        Device device = new Device();
        device.setDeviceToken(deviceToken);
        device.setDeviceUid(deviceUid);

        DeviceResource expected = new DeviceResource();
        expected.setDeviceToken(deviceToken);
        expected.setDeviceUid(deviceUid);

        // When
        when(deviceService.getDeviceByUid(anyString())).thenReturn(device);
        when(deviceConverter.convert(any(Device.class))).thenReturn(expected);

        DeviceResource actual = unit.getDevice(deviceUid);

        // Then
        verify(deviceService).getDeviceByUid(deviceUid);
        verify(deviceConverter).convert(device);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetDeviceByUidAndApplicationId() throws Exception {
        // Given
        String deviceUid = "deviceUid";
        String deviceToken = "deviceToken";
        String applicationId = "deviceUid";

        Device device = new Device();
        device.setDeviceToken(deviceToken);
        device.setDeviceUid(deviceUid);

        DeviceResource expected = new DeviceResource();
        expected.setDeviceToken(deviceToken);
        expected.setDeviceUid(deviceUid);

        // When
        when(deviceService.getDeviceByUidAndApplicationId(anyString(), anyString())).thenReturn(device);
        when(deviceConverter.convert(any(Device.class))).thenReturn(expected);

        DeviceResource actual = unit.getDeviceByUidAndApplication(deviceUid, applicationId);

        // Then
        verify(deviceService).getDeviceByUidAndApplicationId(deviceUid, applicationId);
        verify(deviceConverter).convert(device);

        assertEquals(expected, actual);
    }
}
