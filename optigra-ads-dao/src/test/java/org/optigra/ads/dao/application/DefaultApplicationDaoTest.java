package org.optigra.ads.dao.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.model.application.Application;

@RunWith(MockitoJUnitRunner.class)
public class DefaultApplicationDaoTest {
    
    @Captor
    private ArgumentCaptor<Application> applicationCaptor;
    
    @Mock
    private EntityManager entityManager;
    
    @Mock
    private TypedQuery<Application> typedQuery;
    
    @InjectMocks
    private DefaultApplicationDao unit = new DefaultApplicationDao();

    @Test
    public void testCreateApplication() {
        // Given
        String applicationId = "5th65124l1k241s4d3c2h35";
        Application application = new Application();
        application.setApplicationId(applicationId);
        
        // When
        unit.createApplication(application);
        
        verify(entityManager).persist(applicationCaptor.capture());
        
        // Then
        assertEquals(application, applicationCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateApplicationWithNotNullId() {
        // Given
        Long id = 1L;
        Application application = new Application();
        application.setId(id);
        
        // When
        unit.createApplication(application);
        
        verify(entityManager).persist(applicationCaptor.capture());
        
        // Then
        assertEquals(application, applicationCaptor.getValue());
    }
}
