package org.optigra.ads.facade.converter.notification;

import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.notification.NotificationResource;
import org.springframework.stereotype.Component;

@Component("notificationResourceConverter")
public class NotificationResourceConverter extends AbstractConverter<NotificationResource, Notification> {

	@Override
	public Notification convert(NotificationResource source, Notification target) {
		
		target.setMessage(source.getMessage());
		target.setTitle(source.getTitle());
		
		return target;
	}

	@Override
	public Notification convert(NotificationResource source) {
		return convert(source, new Notification());
	}

}
