package org.optigra.ads.service.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.application.ApplicationDao;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.application.ApplicationStatus;
import org.optigra.ads.model.pagination.PagedResult;

@RunWith(MockitoJUnitRunner.class)
public class AnonymousApplicationServiceTest {

    @Captor
    private ArgumentCaptor<Application> applicationCaptor;

    @Mock
    private ApplicationDao applicationDao;

    @InjectMocks
    private final AnonymousApplicationService unit = new AnonymousApplicationService();

    @Test
    public void testCreateApplication() {
        // Given
        Long userId = 1L;
        Application application = new Application();
        application.setId(userId);

        // When
        unit.createApplication(application);

        verify(applicationDao).create(applicationCaptor.capture());

        // Then
        assertEquals(application, applicationCaptor.getValue());
    }

    @Test
    public void testGetApplications() {
        // Given
        int offset = 0;
        int limit = 20;
        long count = 200;
        Application entity1 = new Application();
        List<Application> entities = Arrays.asList(entity1);
        PagedResult<Application> expected = new PagedResult<Application>(offset, limit, count, entities );

        // When
        when(applicationDao.getApplications(anyInt(), anyInt())).thenReturn(expected);

        PagedResult<Application> actual = unit.getApplications(offset, limit);

        // Then
        verify(applicationDao).getApplications(offset, limit);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetApplicationStatus() {
        // Given
        String applicationId = "appId";
        Application application = new Application();
        ApplicationStatus status = ApplicationStatus.PAID;
        application.setStatus(status );
        String expected = status.name();

        // When
        when(applicationDao.getApplicationById(anyString())).thenReturn(application);

        String actual = unit.getApplicationStatus(applicationId);

        // Then
        verify(applicationDao).getApplicationById(applicationId);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetApplication() {
        // Given
        String applicationId = "appId";
        Application expected = new Application();
        expected.setApplicationId(applicationId);

        // When
        when(applicationDao.getApplicationById(anyString())).thenReturn(expected);

        Application actual = unit.getApplication(applicationId);

        // Then
        verify(applicationDao).getApplicationById(applicationId);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteApplication() {
        // Given
        String applicationId = "gh5f24g5fg43";
        Application application = new Application();
        application.setApplicationId(applicationId);

        // When
        when(applicationDao.getApplicationById(anyString())).thenReturn(application);

        unit.deleteApplication(applicationId);

        // Then
        verify(applicationDao).getApplicationById(applicationId);
        verify(applicationDao).remove(applicationCaptor.capture());

        assertEquals(application, applicationCaptor.getValue());
    }

    @Test
    public void testUpdateApplication() throws Exception {
        // Given
        String applicationId = "applicationId";
        Application application = new Application();
        application.setApplicationId(applicationId);

        // When
        unit.updateApplication(application);

        // Then
        verify(applicationDao).update(applicationCaptor.capture());
        assertEquals(application, applicationCaptor.getValue());
    }
}
