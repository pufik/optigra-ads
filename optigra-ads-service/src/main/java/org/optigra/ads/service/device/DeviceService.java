package org.optigra.ads.service.device;

import org.optigra.ads.model.device.Device;

public interface DeviceService {

	void createDevice(Device device);

	void updateDevice(Device device);

	void deleteDevice(String deviceUid);

	Device getDeviceByUid(String deviceUid);

}
