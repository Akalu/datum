package org.datum.datasource.generators;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

import static org.datum.datasource.generators.Algorithm.*;

@Slf4j
public class EmailGeneratorTest {

	@Test
	public void functionalTest() {
		String mail = EmailGenerator.generateEmail(EMAIL_RANDOM_1);
		log.debug("alg 1: {}", mail);

	}

}
