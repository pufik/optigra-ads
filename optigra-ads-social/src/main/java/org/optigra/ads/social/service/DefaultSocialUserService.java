package org.optigra.ads.social.service;

import java.util.List;
import java.util.Map;

import org.optigra.ads.social.model.SocialUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class DefaultSocialUserService implements SocialUserService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultSocialUserService.class);

    private static final String RESPONSE_PARAMETER = "response";
    private static final String ACCESS_TOKEN_PARAMETER = "access_token";
    private static final String VK_USER_URI_TEMPLATE = "https://api.vk.com/method/users.get?fields=photo_50&access_token={accessToken}&v=5.21";

    private RestTemplate restTemplate;

    private String clientId;

    private String secretKey;

    private String redirectUrl;

    private String requestUrlTemplete;


    @Override
    public SocialUser getUserByOAuthCode(final String code) {
        logger.info("Request VK API with: URL=[{}], clientId=[{}], clientSecret=[{}], code=[{}], redirectUrl=[{}]", requestUrlTemplete, clientId, secretKey, code, redirectUrl);
        Map<String, String> accessResponse = restTemplate.getForObject(requestUrlTemplete, Map.class, clientId, secretKey, code, redirectUrl);
        String token = accessResponse.get(ACCESS_TOKEN_PARAMETER);

        return getUserByOAuthToken(token);
    }

    @Override
    public SocialUser getUserByOAuthToken(final String token) {
        logger.info("Request VK API for user details: URL=[{}], acess_token=[{}]", VK_USER_URI_TEMPLATE, token);
        Map<String, List<Map<String, String>>> userDetails = restTemplate.getForObject(VK_USER_URI_TEMPLATE, Map.class, token);

        SocialUser user = new SocialUser();
        List<Map<String, String>> response = userDetails.get(RESPONSE_PARAMETER);

        Map<String, String> userJson = response.iterator().next();

        user.setId(String.valueOf(userJson.get("id")));
        user.setName(userJson.get("first_name") + " " +userJson.get("last_name"));

        return user;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRequestUrlTemplete() {
        return requestUrlTemplete;
    }

    public void setRequestUrlTemplete(final String requestUrlTemplete) {
        this.requestUrlTemplete = requestUrlTemplete;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(final String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
