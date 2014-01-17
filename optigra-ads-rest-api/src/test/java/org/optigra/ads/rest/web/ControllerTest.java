package org.optigra.ads.rest.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ControllerTest {

	@Test
	public void testGetZero() {

		assertEquals(0, new Controller().getZero());
	}
}
