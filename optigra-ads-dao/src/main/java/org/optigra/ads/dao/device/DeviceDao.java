package org.optigra.ads.dao.device;

import org.optigra.ads.dao.Dao;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.pagination.PagedResult;

public interface DeviceDao extends Dao<Device, Long> {

    Device getDeviceByUid(String deviceUid);

    Device getDeviceByUidAndAplication(String deviceUid, String application);

    /**
     * Get's devices for current application
     * @param application
     * @param start
     * @param limit
     * @return
     */
    PagedResult<Device> getApplicationDevices(Application application, int start, int limit);
}
