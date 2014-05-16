package org.optigra.ads.facade.converter.certification;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.facade.resource.certificate.CertificateResource;
import org.optigra.ads.model.certificate.Certificate;

public class CertificationResourceConverterTest {

	private CertificationResourceConverter unit = new CertificationResourceConverter();
	
	@Test
	public void testConvert() throws Exception {
		// Given
		String password = "password";
		String path = "path";
		String applicationId = "applicationId";
		
		CertificateResource source = new CertificateResource();
		source.setPassword(password);
		source.setPath(path);
		source.setApplicationId(applicationId);

		Certificate expected = new Certificate();
		expected.setPath(path);
		expected.setPassword(password);
		
		// When
		Certificate actual = unit.convert(source);
		actual.setUpdateDate(null);
		
		// Then
		assertEquals(expected, actual);
	}
	
}
