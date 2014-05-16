package org.optigra.ads.service.certificate;

import javax.annotation.Resource;

import org.optigra.ads.dao.application.ApplicationDao;
import org.optigra.ads.dao.certificate.CertificateDao;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.certificate.Certificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("certificateService")
public class DefaultCertificateService implements CertificateService {
	private static final Logger logger = LoggerFactory.getLogger(DefaultCertificateService.class);
	
	@Resource(name = "certificateDao")
	private CertificateDao certificateDao;

	@Resource(name = "applicationDao")
	private ApplicationDao applicationDao;
	
	@Override
	public void createCertificate(String applicationId, Certificate certificate) {
		logger.info("Creating certificate {} for {}", certificate, applicationId);
		
		Application application = applicationDao.getApplicationById(applicationId);
		certificate.setApplication(application);
		certificateDao.create(certificate);
	}

	@Override
	public Certificate getCertificate(Long certificateId) {
		return certificateDao.findById(certificateId);
	}

	@Override
	public void updateCertificate(Certificate certificate) {
		certificateDao.update(certificate);
	}

	@Override
	public Certificate getCertificateByApplication(String applicationId) {
		Application application = applicationDao.getApplicationById(applicationId);
		return certificateDao.getCertificateByApplication(application);
	}

	@Override
	public void deleteCertificate(String applicationId) {
		Application application = applicationDao.getApplicationById(applicationId);
		Certificate certificate = certificateDao.getCertificateByApplication(application);
		certificateDao.remove(certificate);
	}

}
