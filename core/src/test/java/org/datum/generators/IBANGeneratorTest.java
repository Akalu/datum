package org.datum.generators;

import static org.junit.Assert.assertTrue;

import org.datum.generators.IBANGenerator;
import org.datum.model.IBANRecord;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IBANGeneratorTest {

	@Test
	public void functionalTest() {
		log.debug("countries {}", IBANGenerator.getAvailableCountries());
		
		IBANRecord rec = IBANGenerator.generate("GB");
		log.debug("Generated {}", rec.getUnformattedNumber());
		log.debug("Generated {}", rec.getFormattedNumber());
	}
	
	@Test
	public void testCheckSum() {
		IBANRecord rec = IBANGenerator.generate("GB");
		assertTrue(rec.getCheckSum() != 0);
	}

	@Test
	public void testValidation() {
		IBANRecord rec = IBANGenerator.generate("GB");
		assertTrue(rec.getCheckSum() != 0);
		assertTrue(IBANGenerator.valid(rec.getUnformattedNumber()));
	}

}
