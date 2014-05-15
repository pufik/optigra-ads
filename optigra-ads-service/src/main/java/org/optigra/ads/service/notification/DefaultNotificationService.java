package org.optigra.ads.service.notification;

import javax.annotation.Resource;

import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.notification.executor.NotificationBatchExecutor;
import org.springframework.stereotype.Service;

@Service("notificationService")
public class DefaultNotificationService implements NotificationService {
	
	@Resource(name = "apnsBatchExecutor")
	private NotificationBatchExecutor notificationBatchExecutor;

	@Override
	public void send(Application application, Notification notification) {
		notificationBatchExecutor.process(application, notification);
	}

	@Override
	public void send(Device device, Notification notification) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
