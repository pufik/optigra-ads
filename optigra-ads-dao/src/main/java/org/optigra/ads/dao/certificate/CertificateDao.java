package org.optigra.ads.dao.certificate;

import org.optigra.ads.dao.Dao;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.certificate.Certificate;

public interface CertificateDao extends Dao<Certificate, Long> {

	Certificate getCertificateByApplication(Application application);

}
