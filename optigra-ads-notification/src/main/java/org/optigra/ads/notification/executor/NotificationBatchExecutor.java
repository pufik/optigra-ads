package org.optigra.ads.notification.executor;

import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.model.application.Application;

public interface NotificationBatchExecutor {

	void process(Application application, Notification notification);

}
