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
     * REST API WADL
     */
    public static final String WADL = "/application.wadl";

    /**
     * Advertising resource base URI.
     */
    public static final String ID = "/{id:.*}";
    public static final String APPLICATION = "/application";
    public static final String APPLICATION_BY_ID = SLASH + "{appId:.*}";
    public static final String APPLICATION_STATUS = SLASH + "{appId:.*}/status";
    public static final String CONTENT = "/content";
    public static final String USER_CONTENT = "/content/user";

    public static final String USER = "/user";
    public static final String USER_BY_ID = "/{id}";

    public static final String DEVICE = SLASH + "device";
    public static final String DEVICE_APPLICATION = "/{deviceId}/application/{applicationId}";

    public static final String CURRENT = SLASH + "current";

	public static final String APPLICATION_NOTIFICATION = APPLICATION_BY_ID + SLASH + "notification";

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
