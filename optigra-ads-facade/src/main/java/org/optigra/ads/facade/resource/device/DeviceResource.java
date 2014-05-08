package org.optigra.ads.facade.resource.device;

import org.optigra.ads.facade.resource.Resource;
import org.optigra.ads.facade.resource.ResourceUri;

public class DeviceResource extends Resource {

    private Long id;

    private String deviceUid;

    private String deviceToken;

    @Override
    public String getUri() {
        return ResourceUri.DEVICE + ResourceUri.SLASH + deviceUid;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getDeviceUid() {
        return deviceUid;
    }

    public void setDeviceUid(final String deviceUid) {
        this.deviceUid = deviceUid;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(final String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((deviceToken == null) ? 0 : deviceToken.hashCode());
        result = prime * result + ((deviceUid == null) ? 0 : deviceUid.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        DeviceResource other = (DeviceResource) obj;
        if (deviceToken == null) {
            if (other.deviceToken != null)
                return false;
        } else if (!deviceToken.equals(other.deviceToken))
            return false;
        if (deviceUid == null) {
            if (other.deviceUid != null)
                return false;
        } else if (!deviceUid.equals(other.deviceUid))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DeviceResource [id=" + id + ", deviceUid=" + deviceUid + ", deviceToken=" + deviceToken + "]";
    }

}
