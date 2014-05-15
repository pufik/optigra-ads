package org.optigra.ads.notification.reader;

import javax.annotation.Resource;

import org.optigra.ads.dao.device.DeviceDao;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.pagination.BaseSearch;
import org.optigra.ads.model.pagination.PagedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("deviceItemReader")
public class DeviceItemReader implements ItemReader<Application, Device> {
	private static final Logger logger = LoggerFactory.getLogger(DeviceItemReader.class);
	
	@Resource(name = "deviceDao")
	private DeviceDao deviceDao;
	
	@Override
	public PagedResult<Device> getItems(Application data, BaseSearch search) {
		logger.info("Retrieving devices for {} by batch - start : {}, limit : {}", data.getName(), search.getOffset(), search.getLimit());
		return deviceDao.getApplicationDevices(data, search.getOffset(), search.getLimit());
	}

}
