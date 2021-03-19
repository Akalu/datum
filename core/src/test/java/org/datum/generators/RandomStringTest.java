package org.datum.generators;

import static org.junit.Assert.assertNotNull;

import org.datum.generators.RandomString;
import org.junit.Test;

public class RandomStringTest {
	
	@Test
	public void functionalTest() {
		RandomString rs = new RandomString();
		String str = rs.nextString();
		assertNotNull(str);
	}
	
}	
