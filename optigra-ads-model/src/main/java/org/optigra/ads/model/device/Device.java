package org.optigra.ads.model.device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.optigra.ads.model.Model;

@Entity
@Table(name = "device")
public class Device extends Model {
	private static final long serialVersionUID = 7160020850188718058L;

	@Column(name = "device_uid" , unique = true, nullable = false)
	private String deviceUid;
	
	@Column(name = "device_token", nullable = false)
	private String deviceToken;

	public String getDeviceUid() {
		return deviceUid;
	}

	public void setDeviceUid(String deviceUid) {
		this.deviceUid = deviceUid;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((deviceToken == null) ? 0 : deviceToken.hashCode());
		result = prime * result
				+ ((deviceUid == null) ? 0 : deviceUid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
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
		return "Device [deviceUid=" + deviceUid + ", deviceToken="
				+ deviceToken + "]";
	}
	
}
