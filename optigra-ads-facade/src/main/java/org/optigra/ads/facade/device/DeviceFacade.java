package org.optigra.ads.facade.device;

import org.optigra.ads.facade.resource.device.DeviceResource;

public interface DeviceFacade {

    DeviceResource createDevice(DeviceResource deviceResource);

    void updateDevice(String deviceUid, DeviceResource deviceResource);

    void deleteDevice(String deviceUid);

    DeviceResource getDevice(String deviceUid);

    DeviceResource getDeviceByUidAndApplication(String deviceUid, String applicationId);

    void addAplicationForDevice(String deviceUid, String applicationId);
}
