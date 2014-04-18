package org.optigra.ads.dao.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.Query;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.dao.pagination.PagedSearch;
import org.optigra.ads.dao.persistence.PersistenceManager;
import org.optigra.ads.model.Queries;
import org.optigra.ads.model.application.Application;

@RunWith(MockitoJUnitRunner.class)
public class DefaultApplicationDaoTest {

    @Captor
    private ArgumentCaptor<Application> applicationCaptor;

    @Mock
    private PersistenceManager<Application, Long> persistenceManager;

    @InjectMocks
    private final DefaultApplicationDao unit = new DefaultApplicationDao();

    @Test
    public void testCreateApplication() {
        // Given
        String applicationId = "5th65124l1k241s4d3c2h35";
        Application application = new Application();
        application.setApplicationId(applicationId);

        // When
        unit.create(application);

        verify(persistenceManager).create(applicationCaptor.capture());

        // Then
        assertEquals(application, applicationCaptor.getValue());
    }

    @Test
    public void testGetApplications() {
        // Given
        int offset = 1;
        int limit = 20;
        long count = 100;
        Queries query = Queries.FIND_APPLICATIONS;
        Map<String, Object> parameters = Collections.emptyMap();
        Query<Application> jpQuery = new Query<Application>(Application.class, query.getQuery(), parameters );

        PagedSearch<Application> search = new PagedSearch<>(offset, limit, jpQuery);

        Application application1 = new Application();
        List<Application> entities = Arrays.asList(application1 );
        PagedResult<Application> expected = new PagedResult<Application>(offset, limit, count, entities);

        // When
        when(persistenceManager.search(search)).thenReturn(expected);
        PagedResult<Application> actual = unit.getApplications(offset, limit);

        // Then
        verify(persistenceManager).search(search);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetApplicationById() {
        // Given
        String applicationId = "appIdMMM";
        Application expected = new Application();
        expected.setApplicationId(applicationId);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("appId", applicationId);
        Query<Application> query = new Query<Application>(Application.class, Queries.FIND_APPLICATION_BY_ID.getQuery(), parameters);

        when(persistenceManager.executeQuerySingleResult(query)).thenReturn(expected);

        // When
        Application actual = unit.getApplicationById(applicationId);

        // Then
        verify(persistenceManager).executeQuerySingleResult(query);

        assertEquals(expected, actual);
    }

    @Test
    public void deleteApplication() {
        // Given
        Long id = 1L;
        String applicationId = "fsdfgds23523";
        Application application = new Application();
        application.setId(id);
        application.setApplicationId(applicationId);

        // When
        when(persistenceManager.findById(Application.class, id)).thenReturn(application);

        unit.remove(application);

        // Then
        verify(persistenceManager).remove(applicationCaptor.capture());
        assertEquals(application, applicationCaptor.getValue());
    }
}
