package org.optigra.ads.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DaoTest {

	@Test
	public void testGetZero() {

		assertEquals(0, new Dao().getZero());

	}
}