package org.optigra.ads.messaging.sender;

import org.optigra.ads.messaging.model.Email;

public interface EmailSenderAdapter {
	
	void send(Email email);
	
}
