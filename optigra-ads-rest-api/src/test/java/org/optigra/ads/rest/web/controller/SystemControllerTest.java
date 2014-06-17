package org.optigra.ads.rest.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.optigra.ads.rest.web.util.Constants.JAVAX_SERVLET_ERROR_EXCEPTION;
import static org.optigra.ads.rest.web.util.Constants.JAVAX_SERVLET_ERROR_MESSAGE;
import static org.optigra.ads.rest.web.util.Constants.JAVAX_SERVLET_ERROR_STATUS_CODE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.resource.ApiDetailsResource;
import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.ResourceUri;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class SystemControllerTest extends AbstractControllerTest {

    @Mock
    private HttpServletRequest request;

    private final SystemController unit = new SystemController();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }

    @Test
    public void testGetApiDetails() throws Exception {
        ApiDetailsResource apiDetails = new ApiDetailsResource();

        // When
        mockMvc.perform(get(ResourceUri.SLASH)).andExpect(status().isOk()).andExpect(content().string(objectMapper.writeValueAsString(apiDetails)));
    }

    @Test
    public void testHandleException() throws Exception {
        // Given
        Integer errorCode = 401;
        String message = "mmmm";
        RuntimeException exception = new RuntimeException();
        MessageResource messageResource = new MessageResource(MessageType.ERROR, Long.valueOf(errorCode), message);

        ResponseEntity<MessageResource> expectedResponseEntity = new ResponseEntity<MessageResource>(messageResource , HttpStatus.valueOf(errorCode));

        // When
        when(request.getAttribute(JAVAX_SERVLET_ERROR_STATUS_CODE)).thenReturn(errorCode);
        when(request.getAttribute(JAVAX_SERVLET_ERROR_MESSAGE)).thenReturn(message);
        when(request.getAttribute(JAVAX_SERVLET_ERROR_EXCEPTION)).thenReturn(exception);

        ResponseEntity<MessageResource> actualResponseEntity = unit.handleException(errorCode, request);

        // Then
        assertEquals(expectedResponseEntity.toString(), actualResponseEntity.toString());
    }
}
