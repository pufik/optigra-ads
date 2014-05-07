package org.optigra.ads.model.device;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.optigra.ads.model.Model;
import org.optigra.ads.model.application.Application;

@Entity
@Table(name = "device")
public class Device extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "device_uid", unique = true, nullable = false)
    private String deviceUid;

    @Column(name = "device_token", nullable = false)
    private String deviceToken;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "device_application",
               joinColumns = { @JoinColumn(name = "device_id", referencedColumnName = "entity_id") },
               inverseJoinColumns = { @JoinColumn(name = "application_id", referencedColumnName = "entity_id") })
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DeviceAction> actions = new ArrayList<>();

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

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(final List<Application> applications) {
        this.applications = applications;
    }

    public List<DeviceAction> getActions() {
        return actions;
    }

    public void setActions(final List<DeviceAction> actions) {
        this.actions = actions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((deviceToken == null) ? 0 : deviceToken.hashCode());
        result = prime * result + ((deviceUid == null) ? 0 : deviceUid.hashCode());
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
        Device other = (Device) obj;
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
        return true;
    }

    @Override
    public String toString() {
        return "Device [deviceUid=" + deviceUid + ", deviceToken=" + deviceToken + "]";
    }

}
