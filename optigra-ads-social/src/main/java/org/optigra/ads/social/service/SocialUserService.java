package org.optigra.ads.social.service;

import org.optigra.ads.social.model.SocialUser;

public interface SocialUserService {

    /**
     * Authenticate social user by OAuth code.
     *
     * @date Jun 12, 2014
     * @author Iurii Parfeniuk
     * @param code
     *            - OAuth code
     * @return {@link SocialUser}
     */
    SocialUser getUserByOAuthCode(String code);

    /**
     * Authenticate social user by OAuth token.
     *
     * @date Jun 12, 2014
     * @author Iurii Parfeniuk
     * @param code
     *            - OAuth token
     * @return {@link SocialUser}
     */
    SocialUser getUserByOAuthToken(String token);
}
