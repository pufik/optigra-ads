package org.optigra.ads.rest.web.controller;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.dto.ResourceUri;
import org.optigra.ads.facade.dto.UserResource;
import org.optigra.ads.facade.user.UserFacade;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserFacade defaultUserFacade;
    
    @InjectMocks
    private final UserController unit = new UserController();

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }

    @Test
    public void testGetApiDetails() throws Exception {
        // Given
        Long userId = 1L;
        String login = "login";
        UserResource expectedResource = new UserResource();
        expectedResource.setId(userId);
        expectedResource.setLogin(login);

        // When
        when(defaultUserFacade.getUserById(anyLong())).thenReturn(expectedResource);
        
        // Then
        mockMvc.perform(get(ResourceUri.USER + ResourceUri.SLASH + "{id}", userId))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(expectedResource)));
    }
}
