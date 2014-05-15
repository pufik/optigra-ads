package org.optigra.ads.notification.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class ApnsServiceFactoryBean implements FactoryBean<ApnsService> {

	private Resource certificateResource;

	private String certificatePassword;
	
	@Override
	public ApnsService getObject() throws Exception {
		return APNS
			.newService()
			.withCert(
					certificateResource.getInputStream(),
					certificatePassword).withSandboxDestination().build();
	}

	@Override
	public Class<? extends ApnsService> getObjectType() {
		return ApnsService.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setCertificatePassword(String certificatePassword) {
		this.certificatePassword = certificatePassword;
	}

	public void setCertificateResource(Resource certificateResource) {
		this.certificateResource = certificateResource;
	}
	
}
