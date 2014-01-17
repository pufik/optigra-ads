package org.optigra.ads.facade;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FacadeTest {

	@Test
	public void testGetZero() {

		assertEquals(0, new Facade().getZero());
	}
}
