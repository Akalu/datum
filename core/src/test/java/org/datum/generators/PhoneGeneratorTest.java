package org.datum.generators;

import static org.datum.algorithm.Algorithm.PHONE_RANDOM_1;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneGeneratorTest {
	
	@Test
	public void functionalTest() {
		String mail = PhoneGenerator.generatePhone(PHONE_RANDOM_1, 323);
		log.debug("alg 1: {}", mail);

	}
}
