package org.optigra.ads.notification.reader;

import javax.annotation.Resource;

import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.pagination.BaseSearch;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.service.device.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("deviceItemReader")
public class DeviceItemReader implements ItemReader<Application, Device> {
	private static final Logger logger = LoggerFactory.getLogger(DeviceItemReader.class);
	
	@Resource(name = "deviceService")
	private DeviceService deviceService;
	
	@Override
	public PagedResult<Device> getItems(Application data, BaseSearch search) {
		logger.info("Retrieving devices for {} by batch - start : {}, limit : {}", data.getName(), search.getOffset(), search.getLimit());
		return deviceService.getApplicationDevices(data.getApplicationId(), search.getOffset(), search.getLimit());
	}

}
