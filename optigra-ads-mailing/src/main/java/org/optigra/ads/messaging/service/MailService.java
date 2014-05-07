package org.optigra.ads.messaging.service;

import org.optigra.ads.messaging.model.Email;


public interface MailService {

	/**
	 * Simple method, that sends mail with simple text
	 * @param email
	 */
	void send(Email email);
	
	/**
	 * Method, that sends mail with generated html
	 * @param email
	 * @param templateId
	 * @param data
	 * @throws Exception 
	 */
	<T> void send(Email email, String templateId, T data) throws Exception;
}
