package org.optigra.ads.notification.executor;

import javax.annotation.Resource;

import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.pagination.BaseSearch;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.notification.processor.ItemProcessor;
import org.optigra.ads.notification.reader.ItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("apnsBatchExecutor")
public class ApnsBatchExecutor implements NotificationBatchExecutor {
	private static final Logger logger = LoggerFactory.getLogger(ApnsBatchExecutor.class);
	
	@Resource(name = "deviceItemReader")
	private ItemReader<Application, Device> itemReader;
	
	@Resource(name = "itemProcessor")
	private ItemProcessor itemProcessor;
	
	@Value("${batch.limit}")
	private Integer limit;
	
	@Override
	public void process(Application application, Notification notification) {
		logger.info("Starting batch work on sending apns messages");
		long count = 1;
		
		PagedResult<Device> pagedResult = null;
		BaseSearch search = null;
		for(int start = 0; start < count; start += limit) {
			search = new BaseSearch(start, limit);
			
			pagedResult = itemReader.getItems(application, search);
			itemProcessor.process(pagedResult.getEntities(), notification);

			count = pagedResult.getCount();
		}
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
