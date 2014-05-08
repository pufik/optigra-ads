package org.optigra.ads.messaging.service;

import javax.annotation.Resource;

import org.optigra.ads.messaging.model.Email;
import org.optigra.ads.messaging.sender.EmailSenderAdapter;
import org.optigra.ads.messaging.template.TemplateProvider;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("mailService")
@Async
public class DefaultMailService implements MailService {

	@Resource(name = "templateProvider")
	private TemplateProvider templateProvider;

	@Resource(name = "emailSenderAdapter")
	private EmailSenderAdapter senderAdapter;
	
	@Override
	public void send(Email email) {
		senderAdapter.send(email);
	}

	@Override
	public void send(Email email, String templateId, Object data) {
		String renderedTemplate = templateProvider.process(templateId, data);
		email.setContent(renderedTemplate);
		senderAdapter.send(email);
	}

}
