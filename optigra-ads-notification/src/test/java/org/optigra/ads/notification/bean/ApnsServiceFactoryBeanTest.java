package org.optigra.ads.notification.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.notnoop.apns.ApnsService;

public class ApnsServiceFactoryBeanTest {
	
	private ApnsServiceFactoryBean unit;
	
	@Before
	public void setUp() {
		unit = new ApnsServiceFactoryBean(); 
	}
	
	@Test
	public void testGetObject() throws Exception {
		unit.setCertificate("apns_cert.p12");
		unit.setCertificatePassword("cert_pass");
		assertNotNull(unit.getObject());
	}
	
	@Test
	public void testGetObjectType() throws Exception {
		assertEquals(ApnsService.class, unit.getObjectType());
	}
	
	@Test
	public void testIsSingleton() throws Exception {
		assertTrue(unit.isSingleton());
	}
}
