package org.optigra.ads.dao.device;

import org.optigra.ads.dao.Dao;
import org.optigra.ads.model.device.Device;

public interface DeviceDao extends Dao<Device, Long> {

	Device getDeviceByUid(String deviceUid);

}
