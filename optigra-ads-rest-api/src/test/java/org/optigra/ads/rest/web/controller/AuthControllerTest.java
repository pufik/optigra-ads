package org.optigra.ads.rest.web.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.configuration.ConfigurationService;
import org.optigra.ads.facade.resource.user.UserResource;
import org.optigra.ads.facade.user.UserFacade;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest extends AbstractControllerTest {

    @Mock
    private UserFacade userFacade;

    @Mock
    private ConfigurationService configurationService;

    private MockMvc mockMvc;

    @InjectMocks
    private final AuthController unit = new AuthController();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }

    @Test
    public void testVkAuthorizeByCode() throws Exception {
        // Given
        String code = "vkCode";
        String expectedRedirect = "http://something";
        UserResource userResource = new UserResource();

        // When
        when(userFacade.authorizeSocialUserByCode(code)).thenReturn(userResource);
        when(configurationService.getProperty(AuthController.MY_PABLICI_HOME_URL)).thenReturn("http://something");

        // Then
        mockMvc.perform(get("/authorize/vkontakte").param("code", code))
                .andExpect(status().isMovedTemporarily())
                .andExpect(header().string("Location", expectedRedirect));
    }

    @Test
    public void testVkAuthorizeByToken() throws Exception {
        // Given
        String token = "sdasd12sds34sds";
        UserResource userResource = new UserResource();
        userResource.setFullName("user mmm");
        String response = getJson(userResource, false);

        // When
        when(userFacade.authorizeSocialUserByToken(token)).thenReturn(userResource);

        // Then
        mockMvc.perform(get("/authorize/vkontakte/{token}", token))
            .andExpect(status().isOk())
            .andExpect(content().string(response));
    }
}
