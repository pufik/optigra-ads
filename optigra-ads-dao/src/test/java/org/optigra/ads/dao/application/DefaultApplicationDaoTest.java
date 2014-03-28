package org.optigra.ads.dao.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.model.Queries;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;
import org.optigra.ads.security.session.Session;
import org.optigra.ads.security.session.SessionService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultApplicationDaoTest {

    private static final String TABLE_TOKEN = "$table";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM " + TABLE_TOKEN + " a WHERE a IN(%s) ";

    @Captor
    private ArgumentCaptor<Application> applicationCaptor;

    @Mock
    private EntityManager entityManager;

    @Mock
    private SessionService sessionService;

    @Mock
    private TypedQuery<Application> typedQuery;

    @Mock
    private TypedQuery<Long> typedCountQuery;

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

        verify(entityManager).persist(applicationCaptor.capture());

        // Then
        assertEquals(application, applicationCaptor.getValue());
    }

    @Test
    public void testGetApplications() {
        // Given
        int start = 1;
        int offset = 20;
        long count = 100;
        User user1 = new User();
        user1.setRole(UserRole.ADMIN);
        Queries query = Queries.FIND_APPLICATIONS;
        String querySql = String.format(COUNT_QUERY, query.getQuery()).replace(TABLE_TOKEN, Application.class.getSimpleName());
        Application application1 = new Application();
        List<Application> entities = Arrays.asList(application1 );
        PagedResult<Application> expected = new PagedResult<Application>(start, offset, count, entities);

        // When
        when(entityManager.createQuery(eq(query.getQuery()), Matchers.<Class<Application>>any())).thenReturn(typedQuery);
        when(entityManager.createQuery(eq(querySql), Matchers.<Class<Long>>any())).thenReturn(typedCountQuery);
        when(sessionService.getCurrentSession()).thenReturn(new Session(user1));
        when(typedQuery.getResultList()).thenReturn(entities);
        when(typedCountQuery.getSingleResult()).thenReturn(count);

        PagedResult<Application> actual = unit.getApplications(start, offset);

        // Then
        verify(entityManager).createQuery(query.getQuery(), Application.class);
        verify(entityManager).createQuery(querySql, Long.class);

        verify(typedQuery, times(0)).setParameter(anyString(), anyObject());
        verify(typedCountQuery, times(0)).setParameter(anyString(), anyObject());

        assertEquals(expected, actual);
    }

    @Test
    public void testGetApplicationById() {
        // Given
        String applicationId = "appId";
        Application expected = new Application();
        expected.setApplicationId(applicationId);

        // When
        when(entityManager.createNamedQuery(anyString(), Matchers.<Class<Application>>any())).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(expected);

        Application actual = unit.getApplicationById(applicationId);

        // Then
        verify(entityManager).createNamedQuery(Queries.FIND_APPLICATION_BY_ID.getQueryName(), Application.class);
        verify(typedQuery).setParameter("appId", applicationId);

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
        when(entityManager.find(Matchers.<Class<Application>>any(), anyLong())).thenReturn(application);

        unit.remove(application);

        // Then
        verify(entityManager).remove(applicationCaptor.capture());
        assertEquals(application, applicationCaptor.getValue());
    }
}
