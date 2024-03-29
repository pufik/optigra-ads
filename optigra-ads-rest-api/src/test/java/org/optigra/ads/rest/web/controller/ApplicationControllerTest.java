package org.optigra.ads.rest.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.application.ApplicationFacade;
import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.application.ApplicationResource;
import org.optigra.ads.facade.resource.application.ApplicationStatusResource;
import org.optigra.ads.facade.resource.certificate.CertificateResource;
import org.optigra.ads.facade.resource.notification.NotificationResource;
import org.optigra.ads.model.application.ApplicationStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @date Feb 11, 2014
 * @author ivanursul
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationControllerTest extends AbstractControllerTest {

    @Captor
    private ArgumentCaptor<ApplicationResource> applicationCaptor;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @Mock
    private ApplicationFacade applicationFacade;

    @InjectMocks
    private ApplicationController unit;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }

    @Test
    public void testAddAplication() throws Exception {
        // Given
        String name = "application#2";
        String applicationId = "h5gfg543f5gh34fgh";
        ApplicationResource resource = new ApplicationResource();
        String url = "url";
        resource.setName(name);
        resource.setApplicationId(applicationId);
        resource.setStatus(ApplicationStatus.UNPAID);
        resource.setUrl(url);

        // When
        when(applicationFacade.createApplication(any(ApplicationResource.class))).thenReturn(resource);

        String request = getJson(resource, true);
        String response = getJson(resource, false);

        // Then
        mockMvc.perform(post(ResourceUri.APPLICATION)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

        verify(applicationFacade).createApplication(resource);
    }

    @Test
    public void testGetApplications() throws Exception {
        // Given
        long count = 100;
        int offset = 0;
        int limit = 20;
        ApplicationResource applicationResource1 = new ApplicationResource();
        List<ApplicationResource> entities = Arrays.asList(applicationResource1);
        PagedResultResource<ApplicationResource> pagedResult = new PagedResultResource<>(ResourceUri.APPLICATION);
        pagedResult.setCount(count);
        pagedResult.setOffset(offset);
        pagedResult.setLimit(limit);
        pagedResult.setEntities(entities);

        // When
        when(applicationFacade.getApplications(anyInt(), anyInt())).thenReturn(pagedResult);

        // Then
        mockMvc.perform(get(ResourceUri.APPLICATION)).andExpect(status().isOk()).andExpect(content().string(objectMapper.writeValueAsString(pagedResult)));
    }

    @Test
    public void testGetApplicationsWithParameters() throws Exception {
        // Given
        long count = 100;
        int offset = 5;
        int limit = 34;
        ApplicationResource applicationResource1 = new ApplicationResource();
        List<ApplicationResource> entities = Arrays.asList(applicationResource1);
        PagedResultResource<ApplicationResource> pagedResult = new PagedResultResource<>(ResourceUri.APPLICATION);
        pagedResult.setCount(count);
        pagedResult.setOffset(offset);
        pagedResult.setLimit(limit);
        pagedResult.setEntities(entities);

        // When
        when(applicationFacade.getApplications(anyInt(), anyInt())).thenReturn(pagedResult);

        // Then
        mockMvc.perform(get(ResourceUri.APPLICATION).param("start", String.valueOf(offset)).param("offset", String.valueOf(limit))).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(pagedResult)));
    }

    @Test
    public void testCheckApplicationStatus() throws Exception {
        // Given
        String applicationId = "ds4324kj23k5j23bn5";
        ApplicationStatusResource status = new ApplicationStatusResource(applicationId, ApplicationStatus.UNPAID);

        // When
        when(applicationFacade.getApplicationStatus(anyString())).thenReturn(status);

        // Then
        mockMvc.perform(get("/application/{appId}/status", applicationId)).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(status)));
    }

    @Test
    public void testGetApplication() throws Exception {
        // Given
        String applicationId = "appId";
        String name = "name";
        ApplicationResource applicationResource = new ApplicationResource();
        applicationResource.setApplicationId(applicationId);
        applicationResource.setName(name);
        applicationResource.setStatus(ApplicationStatus.PAID);

        // When
        when(applicationFacade.getApplication(anyString())).thenReturn(applicationResource);

        // Then
        mockMvc.perform(get("/application/{appId}", applicationId)).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(applicationResource)));
    }

    @Test
    public void testDeleteApplication() throws Exception {
        // Given
        String applicationId = "gh23hg5f2gh";
        MessageResource messageResource = new MessageResource(MessageType.INFO, "Application deleted");

        // Then
        mockMvc.perform(delete("/application/{appId}", applicationId)).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(messageResource)));

        verify(applicationFacade).deleteApplication(stringCaptor.capture());
        assertEquals(applicationId, stringCaptor.getValue());
    }

    @Test
    public void testUpdateApplication() throws Exception {
        // Given
        String applicationId = "Very big applcation Id";
        String groupId = "-54435435346";
        String groupName = "groupName";
        String imageUrl = "imageUrl";
        String name = "name";
        String url = "url";

        ApplicationResource applicationResource = new ApplicationResource();
        applicationResource.setApplicationId(applicationId);
        applicationResource.setGroupId(groupId);
        applicationResource.setGroupName(groupName);
        applicationResource.setImageUrl(imageUrl);
        applicationResource.setName(name);
        applicationResource.setStatus(ApplicationStatus.PENDING);
        applicationResource.setUrl(url);

        // When

        // Then
        String request = getJson(applicationResource, true);

        mockMvc.perform(put("/application/{appId}", applicationId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
            .andExpect(status().isOk());

        verify(applicationFacade).updateApplication(eq(applicationId), applicationCaptor.capture());
        assertEquals(applicationResource, applicationCaptor.getValue());
    }

    @Test
	public void testSendApnsMessage() throws Exception {
		// Given
    	String applicationId = "appId";
    	String message = "some messaage";
    	String title = "title";
    	NotificationResource notificationResource = new NotificationResource();
		notificationResource.setMessage(message);
		notificationResource.setTitle(title);

		MessageResource messageResource = new MessageResource(MessageType.INFO, "Messages are going to be sent");
    	// When
		String request = getJson(notificationResource, true);
		String response = getJson(messageResource, false);

		// Then
    	mockMvc.perform(post("/application/{appId}/notification", applicationId)
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(request))
    		.andExpect(status().isOk())
    		.andExpect(content().string(response));

    	verify(applicationFacade).sendNotificationMessage(applicationId, notificationResource);
	}

    @Test
	public void testCreateCertificate() throws Exception {
		// Given
    	String applicationId = "applicationId";
    	String password = "password";
    	String path = "path";

    	CertificateResource certificateResource = new CertificateResource();
    	certificateResource.setApplicationId(applicationId);
		certificateResource.setPassword(password);
		certificateResource.setPath(path);

		String request = getJson(certificateResource, true);

		// When

		// Then
    	mockMvc.perform(post("/application/{appId}/certificate", applicationId)
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(request))
    		.andExpect(status().isOk());

    	verify(applicationFacade).createCertificate(applicationId, certificateResource);
	}

    @Test
	public void testUpdateCertificate() throws Exception {
		// Given
    	String applicationId = "applicationId";
    	Long certificateId = 1L;
    	String password = "password";
    	String path = "path";

    	CertificateResource certificateResource = new CertificateResource();
    	certificateResource.setApplicationId(applicationId);
		certificateResource.setPassword(password);
		certificateResource.setPath(path);

		// When
		String request = getJson(certificateResource, true);

		// Then
    	mockMvc.perform(put("/application/{appId}/certificate/{certificateId}", applicationId, certificateId)
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(request))
    		.andExpect(status().isOk());

    	verify(applicationFacade).updateCertificate(applicationId, certificateId, certificateResource);
	}

    @Test
	public void testGetCertificate() throws Exception {
		// Given
    	Long id = 1L;
    	String applicationId = "applicationId";
    	String password = "password";
    	String path = "/path/to/file";

		CertificateResource resource = new CertificateResource();
		resource.setPassword(password);
		resource.setPath(path);
		resource.setId(id);
		resource.setApplicationId(applicationId);

		String response = getJson(resource, false);

		// When
    	when(applicationFacade.getCertificate(anyString())).thenReturn(resource);

		// Then
    	mockMvc.perform(get("/application/{appId}/certificate", applicationId))
    		.andExpect(status().isOk())
    		.andExpect(content().string(response));
	}

    @Test
	public void testDeleteCertificate() throws Exception {
		// Given
    	String applicationId = "appId";

		// Then
    	mockMvc.perform(delete("/application/{appId}/certificate", applicationId))
    		.andExpect(status().isOk());
    	verify(applicationFacade).deleteCertificate(applicationId);
	}
}
