package org.optigra.ads.notification.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.notification.converter.NotificationMessageConverter;

public class NotificationMessageConverterTest {

	private NotificationMessageConverter unit = new NotificationMessageConverter();
	
	@Test
	public void testConvert() throws Exception {
		// Given
		String message = "message";
		String title = "title";
		Notification source = new Notification();
		source.setMessage(message);
		source.setTitle(title);

		String expected = "title : message";
		
		// When
		String actual = unit.convert(source);

		// Then
		assertEquals(expected, actual);
	}
}
