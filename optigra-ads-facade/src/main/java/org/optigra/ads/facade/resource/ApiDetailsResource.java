package org.optigra.ads.facade.resource;

import java.util.Arrays;
import java.util.List;

/**
 * Resource for API details.
 *
 * @author Iurii Parfeniuk
 */
public final class ApiDetailsResource extends Resource {

    private static final String OPTIGRA_ADS_REST_API = "Optigra Ads REST API";
    private static final String API_VERSION = "1.0";

    private final String version;

    private final String description;

    private final List<String> resources;

    /**
     * Default constructor.
     *
     * @date Jan 24, 2014
     * @author Iurii Parfeniuk
     */

    public ApiDetailsResource() {
        super();
        version = API_VERSION;
        resources = Arrays.asList(ResourceUri.ADVERTISING);
        description = OPTIGRA_ADS_REST_API;
    }

    @Override
    public String getUri() {
        return ResourceUri.SLASH;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getResources() {
        return resources;
    }
}
