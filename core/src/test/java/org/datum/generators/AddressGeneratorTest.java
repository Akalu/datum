package org.datum.generators;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.datum.generators.AddressGenerator;
import org.junit.Test;

public class AddressGeneratorTest {
	
	@Test
	public void functionalTest() {
		String address = AddressGenerator.generateAddressLine1("Elm");
		assertNotNull(address);
		assertTrue(address.contains("Elm"));
	}
}
