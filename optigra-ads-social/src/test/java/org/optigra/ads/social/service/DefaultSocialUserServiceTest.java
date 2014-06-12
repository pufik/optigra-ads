package org.optigra.ads.social.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.social.model.SocialUser;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSocialUserServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private final DefaultSocialUserService unit = new DefaultSocialUserService();

    private static final String VK_USER_URI_TEMPLATE = "https://api.vk.com/method/users.get?fields=photo_50&access_token={accessToken}&v=5.21";
    private final String clientId = "clientMMM";
    private final String secretKey = "secretKeyMMM";
    private final String requestUrlTemplete = "https://oauth.vk.com/access_token?client_id={clientId}&client_secret={secretKey}&code={code}&redirect_uri={redirectUri}";
    private final String redirectUrl = "redirectUrl";
    private final String accessToken = "sdasdw111cdv";
    private Map<String, String> accessResponse;
    private final String userId = "12356563";
    private final String userFirstName = "Petro";
    private final String userLastName = "MMM";

    private Map<String, List<Map<String, String>>> userDetails;
    {
        accessResponse = new HashMap<>();
        accessResponse.put("access_token", accessToken);

        Map<String, String> userInfo =  new HashMap<>();
        userInfo.put("id", userId);
        userInfo.put("first_name", userFirstName);
        userInfo.put("last_name", userLastName);

        userDetails = new HashMap<>();
        userDetails.put("response", Collections.<Map<String, String>> singletonList(userInfo));
    }

    @Before
    public void setUp() {
        unit.setClientId(clientId);
        unit.setSecretKey(secretKey);
        unit.setRequestUrlTemplete(requestUrlTemplete);
        unit.setRedirectUrl(redirectUrl);
    }

    @Test
    public void testGetUserByOAuthCode() throws Exception {
        // Given
        String code = "oauthCode";
        SocialUser expectedSocialUser = new SocialUser();
        expectedSocialUser.setId(userId);
        expectedSocialUser.setName(userFirstName + " " + userLastName);

        // When
        when(restTemplate.getForObject(requestUrlTemplete, Map.class,  clientId, secretKey, code, redirectUrl)).thenReturn(accessResponse);
        when(restTemplate.getForObject(VK_USER_URI_TEMPLATE, Map.class,  accessToken)).thenReturn(userDetails);

        SocialUser actualSocialUser = unit.getUserByOAuthCode(code);

        // Then
        verify(restTemplate).getForObject(requestUrlTemplete, Map.class,  clientId, secretKey, code, redirectUrl);
        verify(restTemplate).getForObject(VK_USER_URI_TEMPLATE, Map.class,  accessToken);

        assertEquals(expectedSocialUser, actualSocialUser);
    }

    @Test
    public void testGetUserByOAuthToken() throws Exception {
        // Given
        SocialUser expectedSocialUser = new SocialUser();
        expectedSocialUser.setId(userId);
        expectedSocialUser.setName(userFirstName + " " + userLastName);

        // When
        when(restTemplate.getForObject(VK_USER_URI_TEMPLATE, Map.class,  accessToken)).thenReturn(userDetails);

        SocialUser actualSocialUser = unit.getUserByOAuthToken(accessToken);

        // Then
        verify(restTemplate).getForObject(VK_USER_URI_TEMPLATE, Map.class,  accessToken);

        assertEquals(expectedSocialUser, actualSocialUser);
    }
}
