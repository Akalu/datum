package org.datum.datasource.generators;

import static org.datum.datasource.generators.EmailGenerator.Algorithm.RANDOM_MAIL_ALG_1;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailGeneratorTest {

	@Test
	public void functionalTest() {
		String mail = EmailGenerator.generateEmail(RANDOM_MAIL_ALG_1);
		log.debug("alg 1: {}", mail);

	}

}
