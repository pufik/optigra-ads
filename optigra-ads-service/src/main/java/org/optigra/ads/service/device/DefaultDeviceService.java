package org.optigra.ads.service.device;

import javax.annotation.Resource;

import org.optigra.ads.dao.device.DeviceDao;
import org.optigra.ads.model.device.Device;
import org.springframework.stereotype.Service;

@Service("deviceService")
public class DefaultDeviceService implements DeviceService {

	@Resource(name = "deviceDao")
	private DeviceDao deviceDao;

	@Override
	public void createDevice(final Device device) {
		deviceDao.create(device);
	}

	@Override
	public void updateDevice(final Device device) {
		deviceDao.update(device);
	}

	@Override
	public Device getDeviceByUid(final String deviceUid) {
		return deviceDao.getDeviceByUid(deviceUid);
	}

	@Override
	public void deleteDevice(final String deviceUid) {
		Device device = deviceDao.getDeviceByUid(deviceUid);
		deviceDao.remove(device);
	}

    @Override
    public Device getDeviceByUidAndApplicationId(final String deviceUid, final String applicationId) {
        return deviceDao.getDeviceByUidAndAplication(deviceUid, applicationId);
    }
}
