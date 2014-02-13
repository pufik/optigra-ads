package org.optigra.ads.facade.resource.application;

import java.io.Serializable;

import org.optigra.ads.facade.resource.Resource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.model.application.ApplicationStatus;

/**
 * @date Feb 11, 2014
 * @author ivanursul
 *
 */
public class ApplicationResource extends Resource implements Serializable {
    private static final long serialVersionUID = -6462829476597342062L;

    private Long id;
    
    private String applicationId;
    
    private ApplicationStatus status;
    
    private String url;
    
    private String name;
    
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(final String applicationId) {
        this.applicationId = applicationId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(final ApplicationStatus status) {
        this.status = status;
    }

    @Override
    public String getUri() {
        return ResourceUri.APPLICATION + ResourceUri.SLASH + id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((applicationId == null) ? 0 : applicationId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
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
        ApplicationResource other = (ApplicationResource) obj;
        if (applicationId == null) {
            if (other.applicationId != null)
                return false;
        } else if (!applicationId.equals(other.applicationId))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationResource [id=" + id + ", applicationId="
                + applicationId + ", status=" + status + ", url=" + url
                + ", name=" + name + "]";
    }
    
}
