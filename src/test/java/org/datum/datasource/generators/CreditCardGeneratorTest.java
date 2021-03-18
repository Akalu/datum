package org.datum.datasource.generators;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

import static org.datum.model.CardType.*;

import org.datum.model.CreditCard;

@Slf4j
public class CreditCardGeneratorTest {

		@Test
		public void functionalTest() {

			CreditCard card1 = CreditCardGenerator.generateCard(VISA);
			CreditCard card2 = CreditCardGenerator.generateCard(MASTERCARD);
			CreditCard card3 = CreditCardGenerator.generateCard(AMERICAN_EXPRESS);
			CreditCard card4 = CreditCardGenerator.generateCard(DISCOVER);
			
			log.debug(card1.toString());
			log.debug(card2.toString());
			log.debug(card3.toString());
			log.debug(card4.toString());
		}

}
