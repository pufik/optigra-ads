package org.optigra.ads.rest.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.facade.resource.user.UserResource;
import org.optigra.ads.facade.user.UserFacade;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest extends AbstractControllerTest {

    @Captor
    private ArgumentCaptor<UserDetailsResource> userDetailsCaptor;
    
    @Mock
    private UserFacade userFacade;
    
    @InjectMocks
    private final UserController unit = new UserController();

    private MockMvc mockMvc;

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
        when(userFacade.getUserById(anyLong())).thenReturn(expectedResource);
        
        // Then
        mockMvc.perform(get(ResourceUri.USER + ResourceUri.SLASH + "{id}", userId))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(expectedResource)));
    }
    
    @Test
    public void testCreateUser() throws Exception {
        // Given
        writeFromFields(true);
        String password = "password";
        String login = "login";
        Long id = 1L;
        UserDetailsResource userDetailsResource = new UserDetailsResource();
        userDetailsResource.setPassword(password);
        userDetailsResource.setLogin(login);
        userDetailsResource.setId(id);
        
        // Then
        mockMvc.perform(post(ResourceUri.USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDetailsResource)))
            .andExpect(status().isOk());
        
        verify(userFacade).createUser(userDetailsCaptor.capture());
        assertEquals(userDetailsResource, userDetailsCaptor.getValue());
        
        writeFromFields(false);
    }
    
    @Test
    public void testGetUsers() throws Exception {
        // Given
        long count = 100;
        UserResource entity1 = new UserResource();
        List<UserResource> entities = Arrays.asList(entity1 );
        int limit = 20;
        int offset = 1;
        
        PagedResultResource<UserResource> expected = new PagedResultResource<>(ResourceUri.USER);
        expected.setCount(count);
        expected.setEntities(entities);
        expected.setLimit(limit);
        expected.setOffset(offset);
        
        // When
        when(userFacade.getUsers(anyInt(), anyInt())).thenReturn(expected);
        
        // Then
        mockMvc.perform(get("/user")
                .param("offset", String.valueOf(offset))
                .param("limit", String.valueOf(limit)))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(expected)));
        
        verify(userFacade).getUsers(offset, limit);
    }
}
