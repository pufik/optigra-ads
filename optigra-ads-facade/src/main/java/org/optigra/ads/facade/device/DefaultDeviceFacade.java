package org.optigra.ads.facade.device;

import java.util.Date;

import javax.annotation.Resource;

import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.device.DeviceResource;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.security.session.SessionService;
import org.optigra.ads.service.application.ApplicationService;
import org.optigra.ads.service.device.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("deviceFacade")
@Transactional
public class DefaultDeviceFacade implements DeviceFacade {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDeviceFacade.class);

    @Resource(name = "deviceResourceConverter")
    private Converter<DeviceResource, Device> deviceResourceConverter;

    @Resource(name = "deviceConverter")
    private Converter<Device, DeviceResource> deviceConverter;

    @Resource(name = "deviceService")
    private DeviceService deviceService;

    @Resource(name = "anonymousApplicationService")
    private ApplicationService applicationService;

    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Override
    public DeviceResource createDevice(final DeviceResource deviceResource) {
        Device device = deviceResourceConverter.convert(deviceResource);
        device.setOwner(sessionService.getCurrentSession().getUser());
        device.setCreateDate(new Date());

        deviceService.createDevice(device);

        return deviceConverter.convert(device);
    }

    @Override
    public void updateDevice(final String deviceUid, final DeviceResource deviceResource) {
        Device device = deviceService.getDeviceByUid(deviceUid);
        deviceResourceConverter.convert(deviceResource, device);
        deviceService.updateDevice(device);
    }

    @Override
    public void deleteDevice(final String deviceUid) {
        deviceService.deleteDevice(deviceUid);
    }

    @Override
    public DeviceResource getDevice(final String deviceUid) {
        Device device = deviceService.getDeviceByUid(deviceUid);
        DeviceResource deviceResource = deviceConverter.convert(device);

        return deviceResource;
    }

    @Override
    public DeviceResource getDeviceByUidAndApplication(final String deviceUid, final String applicationId) {
        Device device = deviceService.getDeviceByUidAndApplicationId(deviceUid, applicationId);
        DeviceResource deviceResource = deviceConverter.convert(device);

        return deviceResource;
    }

    @Override
    public void addAplicationForDevice(final String deviceUid, final String applicationId) {
        logger.info("Attach device to application deviceUID: {}, applicationId: {}", deviceUid, applicationId);

        Device device = deviceService.getDeviceByUid(deviceUid);
        Application application = applicationService.getApplication(applicationId);

        if (!device.getApplications().contains(application)) {
            device.getApplications().add(application);
            deviceService.updateDevice(device);
        }
    }
}