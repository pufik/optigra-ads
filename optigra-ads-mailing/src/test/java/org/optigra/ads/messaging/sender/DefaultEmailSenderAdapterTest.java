package org.optigra.ads.messaging.sender;

import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.messaging.model.Email;
import org.optigra.ads.messaging.model.Recipient;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@RunWith(MockitoJUnitRunner.class)
public class DefaultEmailSenderAdapterTest {

	@Mock
	private MailSender mailSender;
	
	@InjectMocks
	private DefaultEmailSenderAdapter unit = new DefaultEmailSenderAdapter();
	
	@Test
	public void testSend() throws Exception {
		// Given
		String emailAddress = "email@email.email";
		Recipient recipient1 = new Recipient();
		recipient1.setEmail(emailAddress);
		List<Recipient> recipients = Arrays.asList(recipient1);
		String content = "content";
		Email email = new Email();
		email.setContent(content);
		email.setRecipients(recipients);

		SimpleMailMessage message = new SimpleMailMessage();
		String[] to = new String[1];
		to[0] = emailAddress;

		message.setTo(to);
		message.setSubject(email.getTopic());
		message.setText(email.getContent());
		
		// When
		unit.send(email);

		// Then
		verify(mailSender).send(message);
	}

}
