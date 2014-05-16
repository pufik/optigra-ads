package org.optigra.ads.facade.converter.certification;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.certificate.CertificateResource;
import org.optigra.ads.model.certificate.Certificate;
import org.springframework.stereotype.Component;

@Component("certificateConverter")
public class CertificationConverter extends AbstractConverter<Certificate, CertificateResource> {

	@Override
	public CertificateResource convert(Certificate source, CertificateResource target) {
		
		target.setApplicationId(source.getApplication().getApplicationId());
		target.setId(source.getId());
		target.setPassword(source.getPassword());
		target.setPath(source.getPath());
		
		return target;
	}

	@Override
	public CertificateResource convert(Certificate source) {
		return convert(source, new CertificateResource());
	}

}
