package org.optigra.ads.model.device;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.optigra.ads.model.Model;
import org.optigra.ads.model.application.Application;

@Entity
@Table(name = "device_application")
public class DeviceApplication extends Model {
	private static final long serialVersionUID = 6295146331627086813L;

    @ManyToOne
    @JoinColumn(name = "device_id")
	private Device device;
	
    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((application == null) ? 0 : application.hashCode());
		result = prime * result + ((device == null) ? 0 : device.hashCode());
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
		DeviceApplication other = (DeviceApplication) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (device == null) {
			if (other.device != null)
				return false;
		} else if (!device.equals(other.device))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeviceApplication [device=" + device + ", application="
				+ application + "]";
	}
    
}
