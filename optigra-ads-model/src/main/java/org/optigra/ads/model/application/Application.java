package org.optigra.ads.model.application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.optigra.ads.model.Model;
import org.optigra.ads.model.certificate.Certificate;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.model.device.DeviceAction;

/**
 * Application entity.
 *
 * @date Feb 11, 2014
 * @author ivanursul
 */
@Entity
@Table(name = "application")
public class Application extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "applicationId", unique = true)
    private String applicationId;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(name = "url", unique = true)
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "group_short_name")
    private String groupName;

    @Column(name = "image_url")
    private String imageUrl;
    
    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Certificate certificate;

    @ManyToMany(mappedBy = "applications", fetch = FetchType.LAZY)
    private List<Device> devices = new ArrayList<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DeviceAction> actions = new ArrayList<>();
    

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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(final String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(final List<Device> devices) {
        this.devices = devices;
    }

    public List<DeviceAction> getActions() {
        return actions;
    }

    public void setActions(final List<DeviceAction> actions) {
        this.actions = actions;
    }

    public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((applicationId == null) ? 0 : applicationId.hashCode());
        result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
        result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
        result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Application other = (Application) obj;
        if (applicationId == null) {
            if (other.applicationId != null)
                return false;
        } else if (!applicationId.equals(other.applicationId))
            return false;
        if (groupId == null) {
            if (other.groupId != null)
                return false;
        } else if (!groupId.equals(other.groupId))
            return false;
        if (groupName == null) {
            if (other.groupName != null)
                return false;
        } else if (!groupName.equals(other.groupName))
            return false;
        if (imageUrl == null) {
            if (other.imageUrl != null)
                return false;
        } else if (!imageUrl.equals(other.imageUrl))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (status != other.status)
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
		return "Application [applicationId=" + applicationId + ", status="
				+ status + ", url=" + url + ", name=" + name + ", groupId="
				+ groupId + ", groupName=" + groupName + ", imageUrl="
				+ imageUrl + "]";
	}
    
}
