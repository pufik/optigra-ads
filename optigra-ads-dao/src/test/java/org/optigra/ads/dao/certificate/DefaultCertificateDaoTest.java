package org.optigra.ads.dao.certificate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.persistence.PersistenceManager;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.certificate.Certificate;
import org.optigra.ads.model.query.Queries;
import org.optigra.ads.model.query.Query;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCertificateDaoTest {

    @Mock
    private PersistenceManager<Certificate, Long> persistenceManager;
	
    @InjectMocks
    private DefaultCertificateDao unit = new DefaultCertificateDao();
    
    @Test
	public void testGetCertificateByApplication() throws Exception {
		// Given
    	Long id = 1L;
    	Application application = new Application();
		application.setId(id);

		Certificate expected = new Certificate();
		expected.setApplication(application);
		
		// When
		Query<Certificate> jpQuery = new Query<Certificate>(Certificate.class, Queries.FIND_CERTIFICATE_BY_APPLICATION.getQuery(),
				Collections.<String, Object>singletonMap("application", application));
		
		when(persistenceManager.executeQuerySingleResult(Matchers.<Query<Certificate>>any())).thenReturn(expected);
		
    	Certificate actual = unit.getCertificateByApplication(application);

		// Then
    	verify(persistenceManager).executeQuerySingleResult(jpQuery);
    	assertEquals(expected, actual);
	}
}
