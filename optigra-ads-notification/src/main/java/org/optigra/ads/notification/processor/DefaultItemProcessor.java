package org.optigra.ads.notification.processor;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.optigra.ads.apns.model.device.ApnsNotifiableDevice;
import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.model.device.Device;
import org.optigra.ads.notification.converter.Converter;
import org.optigra.ads.notification.service.DeviceNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("itemProcessor")
public class DefaultItemProcessor implements ItemProcessor {

    private static final Logger logger = LoggerFactory.getLogger(DefaultItemProcessor.class);

    @Resource(name = "notificationMessageConverter")
    private Converter<Notification, String> notificationConverter;

    @Resource(name = "apnsDeviceConverter")
    private Converter<Device, ApnsNotifiableDevice> deviceConverter;

    private final Integer sleepTime = BigDecimal.ONE.intValue();

    @Override
    public void process(final DeviceNotificationService<ApnsNotifiableDevice> notificationService, final List<Device> devices, final Notification notification) {
        String message = notificationConverter.convert(notification);
        logger.info("Sending apns, message: {}, batch size: {},", message, devices.size());

        processLoop(devices, message, notificationService);
    }

    private void processLoop(final List<Device> devices, final String message, final DeviceNotificationService<ApnsNotifiableDevice> notificationService) {
        for (Device device : devices) {
            ApnsNotifiableDevice apnsDevice = null;
            try {
                logger.info("Sending message to: {}", device.getDeviceToken());

                apnsDevice = deviceConverter.convert(device);
                notificationService.notify(apnsDevice, message);

                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (Exception e) {
                logger.error(String.format("Exception occurs while sending notification to device: %s", apnsDevice.getDeviceToken()), e);
            }
        }
    }
}
