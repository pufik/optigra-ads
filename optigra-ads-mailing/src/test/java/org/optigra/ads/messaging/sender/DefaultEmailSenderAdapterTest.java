package org.optigra.ads.messaging.sender;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.messaging.model.Email;
import org.optigra.ads.messaging.model.Recipient;
import org.springframework.mail.javamail.JavaMailSender;

@RunWith(MockitoJUnitRunner.class)
public class DefaultEmailSenderAdapterTest {

	@Mock
	private JavaMailSender mailSender;
	
	@Mock
	private MimeMessage mimeMessage;
	
	@InjectMocks
	private DefaultEmailSenderAdapter unit = new DefaultEmailSenderAdapter();

	@Test
	public void testSend() throws Exception {
		// Given
		String topic = "topic";
		String sender = "Sender@gmail.com";

		String emailAddress = "email@email.email";
		Recipient recipient1 = new Recipient();
		recipient1.setEmail(emailAddress);
		
		List<Recipient> recipients = Arrays.asList(recipient1);
		List<Recipient> ccRecipients = Arrays.asList(recipient1);
		String content = "content";
		
		Email email = new Email();
		email.setSender(sender);
		email.setContent(content);
		email.setRecipients(recipients);
		email.setCcRecipients(ccRecipients);
		email.setTopic(topic);

		String[] to = new String[1];
		to[0] = emailAddress;

		// When
		when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
		MimeMessage message = mailSender.createMimeMessage();
		unit.send(email);

		// Then
		verify(mailSender).send(message);
	}

}
