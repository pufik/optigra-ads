package org.optigra.ads.messaging.sender;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.optigra.ads.messaging.model.Email;
import org.optigra.ads.messaging.model.Recipient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component("emailSenderAdapter")
public class DefaultEmailSenderAdapter implements EmailSenderAdapter {

	@Resource(name = "mailSender")
	private MailSender mailSender;
	
	@Value("${mail.from}")
	private String from;
	
	@Override
	public void send(Email email) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		String[] to = getToRecipients(email.getRecipients());

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(email.getTopic());
		message.setText(email.getContent());
		
		mailSender.send(message);
	}

	private String[] getToRecipients(List<Recipient> recipients) {
		Set<String> set = new HashSet<String>();
		
		for (Recipient recipient : recipients) {
			set.add(recipient.getEmail());
		}
		
		return set.toArray(new String[set.size()]);
	}
	
}
