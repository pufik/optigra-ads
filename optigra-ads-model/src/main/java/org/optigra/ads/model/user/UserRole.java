package org.optigra.ads.model.user;

/**
 * @author Iurii Parfeniuk
 * @date Feb 4, 2014
 */
public enum UserRole {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER"), ANONYMOUS("ROLE_ANONYMOUS");

    private final String securityCode;

    private UserRole(final String securityCode) {
        this.securityCode = securityCode;
    }

    public String getSecurityCode() {
        return securityCode;
    }
}
