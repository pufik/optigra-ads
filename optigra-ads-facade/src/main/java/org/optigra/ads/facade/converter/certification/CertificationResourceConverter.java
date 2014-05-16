package org.optigra.ads.facade.converter.certification;

import java.util.Calendar;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.certificate.CertificateResource;
import org.optigra.ads.model.certificate.Certificate;
import org.springframework.stereotype.Component;

@Component("certificateResourceConverter")
public class CertificationResourceConverter extends AbstractConverter<CertificateResource, Certificate> {

	@Override
	public Certificate convert(CertificateResource source, Certificate target) {

		target.setUpdateDate(Calendar.getInstance().getTime());
		target.setPassword(source.getPassword());
		target.setPath(source.getPath());
		
		return target;
	}

	@Override
	public Certificate convert(CertificateResource source) {
		return convert(source, new Certificate());
	}

}
