package org.optigra.ads.search;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SearchEngineTest {
	@Test
	public void testGetZero() {

		assertEquals(0, new SearchEngine().getZero());
	}
}
