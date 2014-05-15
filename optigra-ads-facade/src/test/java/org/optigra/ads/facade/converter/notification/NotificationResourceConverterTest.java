package org.optigra.ads.facade.converter.notification;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.facade.resource.notification.NotificationResource;

public class NotificationResourceConverterTest {

	private NotificationResourceConverter unit = new NotificationResourceConverter();
	
	@Test
	public void testmethodName() throws Exception {
		// Given
		String message = "message";
		String title = "title";
		NotificationResource source = new NotificationResource();
		source.setMessage(message);
		source.setTitle(title);

		Notification expected = new Notification();
		expected.setMessage(message);
		expected.setTitle(title);
		
		// When
		Notification actual = unit.convert(source);

		// Then
		assertEquals(expected, actual);
	}
	
}
