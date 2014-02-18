package org.optigra.ads.notification.bean;

import org.springframework.beans.factory.FactoryBean;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class ApnsServiceFactoryBean implements FactoryBean<ApnsService> {

	private String certificate;
	
	private String certificatePassword;
	
	public ApnsService getObject() throws Exception {
		return APNS
			.newService()
			.withCert(
				Thread.currentThread().getContextClassLoader().getResource(certificate).getPath(),
					certificatePassword).withSandboxDestination().build();
	}

	public Class<? extends ApnsService> getObjectType() {
		return ApnsService.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public void setCertificatePassword(String certificatePassword) {
		this.certificatePassword = certificatePassword;
	}
}
