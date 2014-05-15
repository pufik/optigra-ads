package org.optigra.ads.notification.bean;

import static org.junit.Assert.assertEquals;
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
	public void testGetObjectType() throws Exception {
		assertEquals(ApnsService.class, unit.getObjectType());
	}
	
	@Test
	public void testIsSingleton() throws Exception {
		assertTrue(unit.isSingleton());
	}
}
