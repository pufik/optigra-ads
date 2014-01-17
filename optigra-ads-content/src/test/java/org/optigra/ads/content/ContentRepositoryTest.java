package org.optigra.ads.content;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContentRepositoryTest {

	@Test
	public void testGetZero() {

		assertEquals(0, new ContentRepository().getZero());
	}
}
