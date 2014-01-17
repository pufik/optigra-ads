package org.optigra.ads.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ServiceTest {
	@Test
	public void testGetZero() {

		assertEquals(0, new Service().getZero());
	}
}
