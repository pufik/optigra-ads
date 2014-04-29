package org.optigra.ads.dao.device;

import java.util.HashMap;
import java.util.Map;

import org.optigra.ads.dao.AbstractDao;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.query.Queries;
import org.springframework.stereotype.Repository;

@Repository("deviceDao")
public class DefaultDeviceDao extends AbstractDao<Device, Long> implements DeviceDao {

	private static final String DEVICE_UID = "deviceUid";

	@Override
	protected Class<Device> getEntityClass() {
		return Device.class;
	}

	@Override
	public Device getDeviceByUid(String deviceUid) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(DEVICE_UID, deviceUid);
		
		String query = Queries.FIND_DEVICE_BY_UID.getQuery();
		return executeSingleResultQuery(query, parameters);
	}

}
