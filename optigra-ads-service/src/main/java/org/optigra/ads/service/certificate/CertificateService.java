package org.optigra.ads.service.certificate;

import org.optigra.ads.model.certificate.Certificate;

/**
 * Service for managing certificates.
 * @author ivanursul
 *
 */
public interface CertificateService {

	/**
	 * Creates new certificate.
	 * @param applicationId
	 * @param certificate
	 */
	void createCertificate(String applicationId, Certificate certificate);

	/**
	 * Get's certificate by id
	 * @param certificateId
	 * @return
	 */
	Certificate getCertificate(Long certificateId);

	/**
	 * Updating certificate
	 * @param certificate
	 */
	void updateCertificate(Certificate certificate);

	/**
	 * Get's certificate by application
	 * @param applicationId
	 * @return
	 */
	Certificate getCertificateByApplication(String applicationId);

	/**
	 * Method for deleting certificate
	 * 
	 * @param applicationId
	 */
	void deleteCertificate(String applicationId);

}
