package org.optigra.ads.notification.service.demo;


import java.util.Date;
import java.util.Map;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class ApnsNotificationDemo {
	
	public static void main() {
		ApnsService service = APNS
				.newService()
				.withCert(
						Thread.currentThread().getContextClassLoader()
								.getResource("apns_cert.p12").getPath(),
						"cert_pass").withSandboxDestination().build();

		String payload = APNS.newPayload().alertBody("Hi, man!").build();
		
		String token = "1f0ce2a0e03f4979c56c7ab4165532091c4b907c4e59c99abcb6c52628843ad5";
		
		service.push(token, payload);

		Map<String, Date> inactiveDevices = service.getInactiveDevices();
		
		for (String deviceToken : inactiveDevices.keySet()) {
			Date inactiveAsOf = inactiveDevices.get(deviceToken);
			System.out.println(deviceToken + " is inactive since: " + inactiveAsOf);
		}
	}
}
