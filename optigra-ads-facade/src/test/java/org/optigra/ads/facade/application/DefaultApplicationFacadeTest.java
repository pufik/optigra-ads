package org.optigra.ads.facade.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
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
import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.Resource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.application.ApplicationResource;
import org.optigra.ads.facade.resource.certificate.CertificateResource;
import org.optigra.ads.facade.resource.notification.NotificationResource;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.application.ApplicationStatus;
import org.optigra.ads.model.certificate.Certificate;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.model.user.User;
import org.optigra.ads.security.session.Session;
import org.optigra.ads.security.session.SessionService;
import org.optigra.ads.service.application.ApplicationService;
import org.optigra.ads.service.certificate.CertificateService;
import org.optigra.ads.service.notification.NotificationService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultApplicationFacadeTest {

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @Captor
    private ArgumentCaptor<PagedResult<?>> pagedResultCaptor;

    @Captor
    private ArgumentCaptor<PagedResultResource<? extends Resource>> pagedResultResource;

    @Captor
    private ArgumentCaptor<Application> applicationCaptor;

    @Mock
    private Converter<Application, ApplicationResource> applicationConverter;

    @Mock
    private Converter<PagedResult<?>, PagedResultResource<? extends org.optigra.ads.facade.resource.Resource>> pagedSearchConverter;
    
    @Mock
    private Converter<CertificateResource, Certificate> certificateResourceConverter;

    @Mock
    private Converter<Certificate, CertificateResource> certificateConverter;
    
    @Mock
    private Converter<ApplicationResource, Application> applicationResourceConverter;
    
    @Mock
    private Converter<NotificationResource, Notification> notificationResourceConverter;
    
    @Mock
    private CertificateService certificateService;

    @Mock
    private ApplicationService applicationService;
    
    @Mock
    private NotificationService notificationService;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private final ApplicationFacade unit = new DefaultApplicationFacade();

    @Test
    public void testCreateApplication() {
        // Given
        String name = "application";
        User user = new User();
        ApplicationResource applicationResource = new ApplicationResource();
        applicationResource.setName(name);
        Application application = new Application();
        application.setName(name);
        application.setOwner(user);

        // When
        when(applicationResourceConverter.convert(any(ApplicationResource.class))).thenReturn(application);
        when(applicationConverter.convert(any(Application.class))).thenReturn(applicationResource);
        when(sessionService.getCurrentSession()).thenReturn(new Session(user));

        ApplicationResource actualResource = unit.createApplication(applicationResource);

        verify(applicationService).createApplication(applicationCaptor.capture());
        verify(applicationResourceConverter).convert(applicationResource);
        verify(applicationConverter).convert(application);

        // Then
        assertEquals(application, applicationCaptor.getValue());
        assertEquals(applicationResource, actualResource);
    }

    @Test
    public void testGetApplications() {
        // Given
        int offset = 0;
        int limit = 25;
        long count = 100;
        String uri = ResourceUri.APPLICATION;
        Application applications1 = new Application();
        ApplicationResource applicationsResource1 = new ApplicationResource();
        List<Application> entities = Arrays.asList(applications1);
        List<ApplicationResource> applicationResources = Arrays.asList(applicationsResource1);
        PagedResult<Application> result = new PagedResult<Application>(offset, limit, count, entities);
        PagedResultResource<ApplicationResource> expecteds = new PagedResultResource<>(uri);
        expecteds.setEntities(applicationResources);

        // When
        when(applicationService.getApplications(anyInt(), anyInt())).thenReturn(result);
        when(applicationConverter.convertAll(anyListOf(Application.class))).thenReturn(applicationResources);

        PagedResultResource<ApplicationResource> actuals = unit.getApplications(offset, limit);

        // Then

        verify(pagedSearchConverter).convert(pagedResultCaptor.capture(), pagedResultResource.capture());

        assertEquals(result, pagedResultCaptor.getValue());
        assertEquals(expecteds, actuals);
    }

    @Test
    public void testGetApplicationStatus() {
        // Given
        String applicationId = "ds4324kj23k5j23bn5";
        String expected = null;

        // When
        when(applicationService.getApplicationStatus(anyString())).thenReturn(expected);

        String actual = unit.getApplicationStatus(applicationId);

        // Then
        verify(applicationService).getApplicationStatus(applicationId);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetApplication() {
        // Given
        String applicationId = "appId";
        Long id = 1L;
        String name = "name";
        String url = "www.vk.com/ios-kakashka";

        ApplicationResource expected = new ApplicationResource();
        expected.setApplicationId(applicationId);
        expected.setId(id);
        expected.setName(name);
        expected.setStatus(ApplicationStatus.PENDING);
        expected.setUrl(url);

        Application application = new Application();
        application.setApplicationId(applicationId);
        application.setId(id);
        application.setName(name);
        application.setStatus(ApplicationStatus.PENDING);
        application.setUrl(url);

        // When
        when(applicationService.getApplication(anyString())).thenReturn(application);
        when(applicationConverter.convert(any(Application.class))).thenReturn(expected);

        ApplicationResource actual = unit.getApplication(applicationId);

        // Then
        verify(applicationService).getApplication(applicationId);
        verify(applicationConverter).convert(application);

        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteApplication() {
        // Given
        String applicationId = "gh5f24hg5f43g";

        // When
        unit.deleteApplication(applicationId);

        // Then
        verify(applicationService).deleteApplication(stringCaptor.capture());
        assertEquals(applicationId, stringCaptor.getValue());
    }

    @Test
    public void testUpdateApplication() throws Exception {
        // Given
        String applicationId = "applicationId";
        String url = "url";
        String groupId = "-534534534";
        String groupName = "groupName";
        String imageUrl = "imageUrl";
        String name = "name";

        ApplicationResource applicationResource = new ApplicationResource();
        applicationResource.setApplicationId(applicationId);
        applicationResource.setGroupId(groupId);
        applicationResource.setGroupName(groupName);
        applicationResource.setImageUrl(imageUrl);
        applicationResource.setName(name);
        applicationResource.setUrl(url);
        Application application = new Application();
        application.setApplicationId(applicationId);
        application.setGroupId(groupId);
        application.setGroupName(groupName);
        application.setImageUrl(imageUrl);
        application.setName(name);
        application.setUrl(url );

        // When
        when(applicationService.getApplication(anyString())).thenReturn(application);

        unit.updateApplication(applicationId, applicationResource);

        // Then
        verify(applicationResourceConverter).convert(eq(applicationResource), eq(application));
        verify(applicationService).updateApplication(applicationCaptor.capture());
        assertEquals(application, applicationCaptor.getValue());
    }
    
    @Test
	public void testSendNotificationMessage() throws Exception {
		// Given
    	String applicationId = "appId";
    	String message = "Message";
    	String title = "title";
    	
    	NotificationResource notificationResource = new NotificationResource();
		notificationResource.setMessage(message);
		notificationResource.setTitle(title);

		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setTitle(title);

		Application application = new Application();
		application.setApplicationId(applicationId);
		
		// When
		when(notificationResourceConverter.convert(any(NotificationResource.class))).thenReturn(notification);
		when(applicationService.getApplication(anyString())).thenReturn(application);
		
    	unit.sendNotificationMessage(applicationId, notificationResource);

		// Then
    	verify(applicationService).getApplication(applicationId);
    	verify(notificationResourceConverter).convert(notificationResource);
    	verify(notificationService).send(application, notification);
	}
    
    @Test
	public void testCreateCertificate() throws Exception {
		// Given
    	String applicationId = "applicationId";
    	String password = "password";
    	CertificateResource resource = new CertificateResource();
    	resource.setApplicationId(applicationId);
		resource.setPassword(password);

		Certificate certificate = new Certificate();
		certificate.setPassword(password);
		User user = new User();
		Session session = new Session(user);
		
		// When
		when(sessionService.getCurrentSession()).thenReturn(session);
		when(certificateResourceConverter.convert(any(CertificateResource.class))).thenReturn(certificate);
		
    	unit.createCertificate(applicationId, resource);

		// Then
    	verify(certificateResourceConverter).convert(resource);
    	verify(certificateService).createCertificate(applicationId, certificate);
	}
    
    @Test
	public void testUpdateCertificate() throws Exception {
		// Given
    	String applicationId = "applicationId";
    	Long certificateId = 1L;
    	String path = "path";
    	String password = "password";

    	CertificateResource resource = new CertificateResource();
		resource.setPath(path);
		resource.setPassword(password);

		Certificate certificate = new Certificate();
		certificate.setPath(path);
		certificate.setPassword(password);
		
		// When
		when(certificateService.getCertificate(anyLong())).thenReturn(certificate);
		
    	unit.updateCertificate(applicationId, certificateId, resource);

		// Then
    	verify(certificateService).getCertificate(certificateId);
    	verify(certificateResourceConverter).convert(resource, certificate);
    	verify(certificateService).updateCertificate(certificate);
	}
    
    @Test
	public void testGetCertificate() throws Exception {
		// Given
    	String applicationId = "appId";
    	Long certificateId = 1L;

    	CertificateResource expected = new CertificateResource();
    	expected.setApplicationId(applicationId);
    	
		Certificate certificate = new Certificate();
		certificate.setId(certificateId);

		// When
    	when(certificateService.getCertificateByApplication(anyString())).thenReturn(certificate);
    	when(certificateConverter.convert(any(Certificate.class))).thenReturn(expected);
    	CertificateResource actual = unit.getCertificate(applicationId);

		// Then
    	assertEquals(expected, actual);
	}
    
    @Test
	public void testDeleteCertificate() throws Exception {
		// Given
    	String applicationId = "appId";

		// When
    	unit.deleteCertificate(applicationId);

		// Then
    	verify(certificateService).deleteCertificate(applicationId);
	}
}
