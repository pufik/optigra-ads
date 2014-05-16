package org.optigra.ads.service.certificate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.application.ApplicationDao;
import org.optigra.ads.dao.certificate.CertificateDao;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.certificate.Certificate;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCertificateServiceTest {

	@Mock
	private CertificateDao certificateDao;

	@Mock
	private ApplicationDao applicationDao;
	
	@InjectMocks
	private DefaultCertificateService unit = new DefaultCertificateService();

	@Test
	public void testCreateCertificate() throws Exception {
		// Given
		String applicationId = "applicationId";
		String path = "path";
		String password = "password";
		
		Application application = new Application();
		application.setApplicationId(applicationId);

		Certificate certificate = new Certificate();
		certificate.setPassword(password);
		certificate.setPath(path);
		certificate.setApplication(application);
		
		// When
		when(applicationDao.getApplicationById(anyString())).thenReturn(application);
		
		unit.createCertificate(applicationId, certificate);

		// Then
		verify(applicationDao).getApplicationById(applicationId);
		verify(certificateDao).create(certificate);
	}
	
	@Test
	public void testGetCertificate() throws Exception {
		// Given
		Long certificateId = 1L;
		Certificate certificate = new Certificate();
		certificate.setId(certificateId);

		// When
		when(certificateDao.findById(anyLong())).thenReturn(certificate);
		
		unit.getCertificate(certificateId);

		// Then
		verify(certificateDao).findById(certificateId);
	}
	
	@Test
	public void testUpdateCertificate() throws Exception {
		// Given
		Long certificateId = 1L;
		Certificate certificate = new Certificate();
		certificate.setId(certificateId);

		// When
		unit.updateCertificate(certificate);

		// Then
		verify(certificateDao).update(certificate);
	}
	
	@Test
	public void testGetCertificateByApplication() throws Exception {
		// Given
		String applicationId = "appId";
		Application application = new Application();
		application.setApplicationId(applicationId);

		Certificate expected = new Certificate();
		expected.setApplication(application);
		
		// When
		when(applicationDao.getApplicationById(anyString())).thenReturn(application);
		when(certificateDao.getCertificateByApplication(any(Application.class))).thenReturn(expected);
		Certificate actual = unit.getCertificateByApplication(applicationId);

		// Then
		verify(applicationDao).getApplicationById(applicationId);
		verify(certificateDao).getCertificateByApplication(application);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDeleteCertificate() throws Exception {
		// Given
		String applicationId = "appId";

		Application application = new Application();
		application.setApplicationId(applicationId);
		Certificate certificate = new Certificate();
		certificate.setApplication(application);
		
		// When
		when(applicationDao.getApplicationById(applicationId)).thenReturn(application);
		when(certificateDao.getCertificateByApplication(any(Application.class))).thenReturn(certificate);
		unit.deleteCertificate(applicationId);

		// Then
		verify(applicationDao).getApplicationById(applicationId);
		verify(certificateDao).getCertificateByApplication(application);
		verify(certificateDao).remove(certificate);
	}
}
