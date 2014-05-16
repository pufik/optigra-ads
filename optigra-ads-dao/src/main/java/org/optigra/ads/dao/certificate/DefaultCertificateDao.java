package org.optigra.ads.dao.certificate;

import java.util.Collections;

import org.optigra.ads.dao.AbstractDao;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.certificate.Certificate;
import org.optigra.ads.model.query.Queries;
import org.springframework.stereotype.Repository;

@Repository("certificateDao")
public class DefaultCertificateDao extends AbstractDao<Certificate, Long> implements CertificateDao {

	@Override
	protected Class<Certificate> getEntityClass() {
		return Certificate.class;
	}

	@Override
	public Certificate getCertificateByApplication(Application application) {
		String query = Queries.FIND_CERTIFICATE_BY_APPLICATION.getQuery();
		return executeSingleResultQuery(query, Collections.<String, Object>singletonMap("application", application));
	}

}
