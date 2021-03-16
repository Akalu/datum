package org.datum.datasource.generators;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

import static org.datum.datasource.generators.CreditCardGenerator.Algorithm.*;

import org.datum.datasource.model.CreditCard;

@Slf4j
public class CreditCardGeneratorTest {

		@Test
		public void functionalTest() {

			CreditCard card1 = CreditCardGenerator.generateCard(VISA_ALG_1);
			CreditCard card2 = CreditCardGenerator.generateCard(MASTERCARD_ALG_1);
			CreditCard card3 = CreditCardGenerator.generateCard(AMERICAN_EXPRESS_ALG_1);
			CreditCard card4 = CreditCardGenerator.generateCard(DISCOVER_ALG_1);
			
			log.debug(card1.toString());
			log.debug(card2.toString());
			log.debug(card3.toString());
			log.debug(card4.toString());
		}

}
