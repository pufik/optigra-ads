package org.optigra.ads.facade.resource;

/**
 * Container for resource URI.
 *
 * @author Iurii Parfeniuk
 */
public final class ResourceUri {


    /**
     * Slash symbo.
     */
    public static final String SLASH = "/";

    /**
     * Advertising resource base URI.
     */
    public static final String ADVERTISING = "/advertising";
    public static final String APPLICATION = "/application";
    public static final String APPLICATION_BY_ID = SLASH + "{appId:.*}";
    public static final String APPLICATION_STATUS = SLASH + "{appId:.*}/status";
    public static final String USER = "/user";


    /**
     * Default constructor.
     *
     * @date Jan 24, 2014
     * @author Iurii Parfeniuk
     */
    private ResourceUri() {
        super();
    }
}
