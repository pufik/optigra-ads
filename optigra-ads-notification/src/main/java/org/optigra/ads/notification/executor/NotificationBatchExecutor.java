package org.optigra.ads.notification.executor;

import org.optigra.ads.apns.model.device.ApnsNotifiableDevice;
import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.notification.service.DeviceNotificationService;

public interface NotificationBatchExecutor {

	void process(Application application, Notification notification, DeviceNotificationService<ApnsNotifiableDevice> apnsNotificationService);

}
