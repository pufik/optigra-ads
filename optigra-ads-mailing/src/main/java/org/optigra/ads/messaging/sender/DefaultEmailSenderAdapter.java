package org.optigra.ads.messaging.sender;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.optigra.ads.messagin.exception.MailException;
import org.optigra.ads.messaging.model.Email;
import org.optigra.ads.messaging.model.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component("emailSenderAdapter")
public class DefaultEmailSenderAdapter implements EmailSenderAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultEmailSenderAdapter.class);
	
	@Resource(name = "mailSender")
	private JavaMailSender mailSender;
	
	@Override
	public void send(Email email) {
		Assert.notNull(mailSender, "Email object cannot be null");
		LOG.info(MessageFormat.format("Sending mail: {0}", email));
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message);
		
		try {
			messageHelper.setFrom(email.getSender());
			messageHelper.setTo(getRecipientsArray(email.getRecipients()));
			messageHelper.setCc(getRecipientsArray(email.getCcRecipients()));
			messageHelper.setSubject(email.getTopic());
			messageHelper.setText(email.getContent(), email.isHtml());
		
			mailSender.send(message);
		} catch (MessagingException e) {
			LOG.error("Exception occurs during email sending", e);
			throw new MailException();
		}
		
	}

	private String[] getRecipientsArray(List<Recipient> recipients) {
		Set<String> set = new HashSet<String>();
		
		for (Recipient recipient : recipients) {
			set.add(recipient.getEmail());
		}
		
		return set.toArray(new String[set.size()]);
	}
	
}
