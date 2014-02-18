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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((destinationUrl == null) ? 0 : destinationUrl.hashCode());
        result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = prime * result + ((logoUrl == null) ? 0 : logoUrl.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AdvertisingResource other = (AdvertisingResource) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (destinationUrl == null) {
            if (other.destinationUrl != null)
                return false;
        } else if (!destinationUrl.equals(other.destinationUrl))
            return false;
        if (imageUrl == null) {
            if (other.imageUrl != null)
                return false;
        } else if (!imageUrl.equals(other.imageUrl))
            return false;
        if (logoUrl == null) {
            if (other.logoUrl != null)
                return false;
        } else if (!logoUrl.equals(other.logoUrl))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (uid == null) {
            if (other.uid != null)
                return false;
        } else if (!uid.equals(other.uid))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AdvertisingResource [uid=" + uid + ", title=" + title + ", description=" + description + ", logoUrl=" + logoUrl + ", imageUrl=" + imageUrl
                + ", destinationUrl=" + destinationUrl + "]";
    }
    
}
