package org.optigra.ads.notification.converter;

import java.text.MessageFormat;

import org.optigra.ads.apns.model.notification.Notification;
import org.springframework.stereotype.Component;

@Component("notificationMessageConverter")
public class NotificationMessageConverter implements Converter<Notification, String> {

	@Override
	public String convert(Notification source) {
		String message = MessageFormat.format("{0} : {1}", source.getTitle(), source.getMessage());
		return message;
	}

}
