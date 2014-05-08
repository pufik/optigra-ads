package org.optigra.ads.messaging.model;

import java.util.List;

public class Email {

	private List<Recipient> recipients;

	private List<Recipient> ccRecipients;
	
	private String sender;
	
	private String topic;
	
	private String content;
	
	private boolean isHtml;

	public List<Recipient> getCcRecipients() {
		return ccRecipients;
	}

	public void setCcRecipients(List<Recipient> ccRecipients) {
		this.ccRecipients = ccRecipients;
	}

	public List<Recipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<Recipient> recipients) {
		this.recipients = recipients;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public boolean isHtml() {
		return isHtml;
	}

	public void setHtml(boolean isHtml) {
		this.isHtml = isHtml;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ccRecipients == null) ? 0 : ccRecipients.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + (isHtml ? 1231 : 1237);
		result = prime * result
				+ ((recipients == null) ? 0 : recipients.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (ccRecipients == null) {
			if (other.ccRecipients != null)
				return false;
		} else if (!ccRecipients.equals(other.ccRecipients))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (isHtml != other.isHtml)
			return false;
		if (recipients == null) {
			if (other.recipients != null)
				return false;
		} else if (!recipients.equals(other.recipients))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Email [recipients=" + recipients + ", ccRecipients="
				+ ccRecipients + ", sender=" + sender + ", topic=" + topic
				+ ", content=" + content + ", isHtml=" + isHtml + "]";
	}

}
