package org.optigra.ads.service.device;

import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.pagination.PagedResult;

public interface DeviceService {

    void createDevice(Device device);

    void updateDevice(Device device);

    void deleteDevice(String deviceUid);

    Device getDeviceByUid(String deviceUid);

    Device getDeviceByUidAndApplicationId(String deviceUid, String applicationId);

	PagedResult<Device> getApplicationDevices(String applicationId, int offset, int limit);

}
