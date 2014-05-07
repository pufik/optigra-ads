package org.optigra.ads.messaging.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.messaging.model.Email;
import org.optigra.ads.messaging.model.Recipient;
import org.optigra.ads.messaging.sender.EmailSenderAdapter;
import org.optigra.ads.messaging.template.TemplateProvider;
import org.optigra.ads.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class DefaultMailServiceTest {

	@Mock
	private TemplateProvider templateProvider;
	
	@Mock
	private EmailSenderAdapter senderAdapter;
	
	@InjectMocks
	private DefaultMailService unit = new DefaultMailService();
	
	@Test
	public void testSend() throws Exception {
		// Given
		String emailAddress = "simple.mail@gmail.com";
		Recipient recipient = new Recipient();
		recipient.setEmail(emailAddress);
		
		List<Recipient> recipients = Arrays.asList(recipient);
		String content = "Simple text";
		Email email = new Email();
		email.setContent(content);
		email.setRecipients(recipients);

		// When
		unit.send(email);

		// Then
		verify(senderAdapter).send(email);
	}
	
	@Test
	public void testSendWithTemplate() throws Exception {
		// Given
		String emailAddress = "simple.mail@gmail.com";
		Recipient recipient = new Recipient();
		recipient.setEmail(emailAddress);
		
		List<Recipient> recipients = Arrays.asList(recipient);
		String content = "Simple text";
		Email email = new Email();
		email.setContent(content);
		email.setRecipients(recipients);
		
		String templateId = "templateId";
		String fullName = "Name Surname";
		String login = "login";
		User data = new User();
		data.setEmail(emailAddress);
		data.setFullName(fullName);
		data.setLogin(login);
		
		String renderedTemplate = "rendered tempalte";
		// When
		when(templateProvider.process(anyString(), any())).thenReturn(renderedTemplate);
		unit.send(email, templateId, data);

		// Then
		verify(templateProvider).process(templateId, data);
		assertEquals(renderedTemplate, email.getContent());
	}
}
