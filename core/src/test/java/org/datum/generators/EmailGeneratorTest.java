package org.datum.generators;

import static org.datum.algorithm.Algorithm.*;

import org.datum.generators.EmailGenerator;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailGeneratorTest {

	@Test
	public void functionalTest() {
		String mail = EmailGenerator.generateEmail(EMAIL_RANDOM_1);
		log.debug("alg 1: {}", mail);

	}

}
