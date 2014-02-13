package org.optigra.ads.facade.resource;

/**
 * Resource for advertising.
 *
 * @author Iurii Parfeniuk
 */
public class AdvertisingResource extends Resource {

    private Long uid;

    private String title;

    private String description;

    private String logoUrl;

    private String imageUrl;

    private String destinationUrl;

    public Long getUid() {
        return uid;
    }

    public void setUid(final Long uid) {
        this.uid = uid;
    }

    @Override
    public String getUri() {
        return ResourceUri.ADVERTISING + ResourceUri.SLASH + uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(final String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDestinationUrl() {
        return destinationUrl;
    }

    public void setDestinationUrl(final String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }
}
