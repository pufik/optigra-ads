package org.optigra.ads.facade.converter.certification;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.facade.resource.certificate.CertificateResource;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.certificate.Certificate;

public class CertificationConverterTest {

	private CertificationConverter unit = new CertificationConverter();
	
	@Test
	public void testConvert() throws Exception {
		// Given
		String applicationId = "applicationId";
		Long id = 1L;
		String password = "password";
		String path = "path";
		
		Application application = new Application();
		application.setApplicationId(applicationId);
		Certificate source = new Certificate();
		source.setApplication(application);
		source.setId(id);
		source.setPassword(password);
		source.setPath(path);
		
		CertificateResource expected = new CertificateResource();
		expected.setApplicationId(applicationId);
		expected.setId(id);
		expected.setPassword(password);
		expected.setPath(path);
		
		// When
		CertificateResource actual = unit.convert(source);

		// Then
		assertEquals(expected, actual);
	}
}
