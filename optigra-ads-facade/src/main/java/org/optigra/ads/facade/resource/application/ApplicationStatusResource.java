package org.optigra.ads.facade.resource.application;

import org.optigra.ads.facade.resource.Resource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.model.application.ApplicationStatus;

public class ApplicationStatusResource extends Resource {

    private String applicationId;

    private ApplicationStatus status;

    public ApplicationStatusResource() {
        super();
    }

    public ApplicationStatusResource(final String applicationId, final ApplicationStatus status) {
        super();
        this.applicationId = applicationId;
        this.status = status;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(final String applicationId) {
        this.applicationId = applicationId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(final ApplicationStatus status) {
        this.status = status;
    }

    @Override
    public String getUri() {
        return new StringBuilder().append(ResourceUri.APPLICATION).append(ResourceUri.SLASH).append(applicationId).append(ResourceUri.STATUS).toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((applicationId == null) ? 0 : applicationId.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        ApplicationStatusResource other = (ApplicationStatusResource) obj;
        if (applicationId == null) {
            if (other.applicationId != null)
                return false;
        } else if (!applicationId.equals(other.applicationId))
            return false;
        if (status != other.status)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationStatusResource [applicationId=" + applicationId + ", status=" + status + "]";
    }
}
