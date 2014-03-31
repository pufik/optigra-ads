package org.optigra.ads.rest.web.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.content.ContentFacade;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @date Mar 20, 2014
 * @author ivanursul
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ContentControllerTest extends AbstractControllerTest {
    
    @Mock
    private ContentFacade contentFacade;
    
    @InjectMocks
    private ContentController unit;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }
    
    @Test
    public void testGetContentByPath() throws Exception {
        // Given
        String contentPath = "/contentPath";
        String content = "very large content";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        
        // When
        when(contentFacade.getContentByPath(anyString())).thenReturn(inputStream);

        // Then
        mockMvc.perform(get("/content")
                .param("contentPath", contentPath))
            .andExpect(status().isOk());
        
        verify(contentFacade).getContentByPath(contentPath);
    }
}
