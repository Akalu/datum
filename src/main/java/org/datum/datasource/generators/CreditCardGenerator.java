package org.datum.datasource.generators;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.datum.model.CardType;
import org.datum.model.CreditCard;

import static org.datum.datasource.generators.CommonGenerator.*;

/**
 * Implements logic for card data
 * 
 * @author akaliutau
 *
 */
public class CreditCardGenerator {

	/**
	 * Credit Card Issuer Starts With ( IIN Range ) Length ( Number of digits )
	 */
	public String[][] descriptors = { 
			{ "American Express", "34, 37", "15" },
			{ "Diners Club - Carte Blanche", "300, 301, 302, 303, 304, 305", "14" },
			{ "Diners Club - International", "36", "14" }, 
			{ "Diners Club - USA & Canada", "54", "16" },
			{ "Discover", "6011, 622126 to 622925, 644, 645, 646, 647, 648, 649, 65", "16-19" },
			{ "InstaPayment", "637, 638, 639", "16" }, 
			{ "JCB", "3528 to 3589", "16-19" },
			{ "Maestro", "5018, 5020, 5038, 5893, 6304, 6759, 6761, 6762, 6763", "16-19" },
			{ "MasterCard", "51, 52, 53, 54, 55, 222100-272099", "16" }, 
			{ "Visa", "4", "13-16, 16-19" },
			{ "Visa Electron", "4026, 417500, 4508, 4844, 4913, 4917", "16" }

	};


	/**
	 * Factory of available algorithms
	 * 
	 * @param algorithm
	 * @return
	 */

	private final static Map<CardType, Supplier<int[]>> algs = new HashMap<>();

	static {
		algs.put(CardType.VISA, () -> generateVisaAlgorithm());
		algs.put(CardType.AMERICAN_EXPRESS, () -> generateAmericanExpressAlgorithm());
		algs.put(CardType.MASTERCARD, () -> generateMasterCardAlgorithm());
		algs.put(CardType.DISCOVER, () -> generateDiscoverAlgorithm());
	}
	
	private final static RandomString randomDigitString = new RandomString(3, new SecureRandom(), RandomString.digits);
	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/YY");

	public static CreditCard generateCard() {
		CardType alg = getAny(CardType.values());
		return generateCard(alg);
	}

	public static CreditCard generateCard(CardType algorithm) {
		int[] prefill = algs.get(algorithm).get();
		LocalDate date = getRandomDate(1000);// any date in the range from now up to Roughly 3 years from now
		CreditCard card = new CreditCard();
		card.setNumber(getNumber(prefill));
		card.setType(algorithm);
		card.setExpdate(date.format(formatter));
		card.setSecurityCode(randomDigitString.nextString());
		
		return card;
	}

	/**
	 * Visa
	 */
	private static int[] generateVisaAlgorithm() {
		int[] prefill = new int[15];
		prefill[0] = 4;
		fillRange(1, 15, prefill);
		return prefill;
	}

	/**
	 * American Express
	 */

	static final int[] ae = { 4, 7 };

	private static int[] generateAmericanExpressAlgorithm() {
		int[] prefill = new int[15];
		prefill[0] = 3;
		prefill[1] = getAny(ae);
		fillRange(2, 15, prefill);
		return prefill;
	}

	/**
	 * MasterCard
	 */

	static final int[] mc = { 0, 1, 2, 3, 4, 5 };

	private static int[] generateMasterCardAlgorithm() {
		int[] prefill = new int[16];
		prefill[0] = 5;
		prefill[1] = getAny(mc);
		fillRange(2, 16, prefill);
		return prefill;
	}


	/**
	 * Discover
	 */

	private static int[] generateDiscoverAlgorithm() {
		int[] prefill = new int[15];
		prefill[0] = 6;
		prefill[1] = 0;
		prefill[2] = 1;
		prefill[3] = 1;
		fillRange(4, 15, prefill);
		return prefill;
	}

	private static void fillRange(int from, int to, int[] digits) {
		for (int i = from; i < to; i++) {
			digits[i] = random.nextInt(10);
		}
	}

	/**
	 * From the generated prefills, calculates the last checksum digits that needs
	 * to be added
	 * 
	 * @return number as String
	 */
	private static String getNumber(int[] cardNumber) {
		StringBuilder sb = new StringBuilder();
		int checkSum = 0;
		int n = cardNumber.length;
		
		// Reason for this check offset is to figure out whether the final list is going
		// to be even or odd which will affect calculating the check_sum.
		// This is to avoid reversing the list back and forth which is specified on the
		// Luhn algorithm.
		int checkOffset = (n + 1) % 2;
		
		for (int index = 0; index < n; index++) {
			if ((index + checkOffset) % 2 == 0) {
				int doubled_num = cardNumber[index] * 2;
				checkSum += doubled_num > 9 ? doubled_num - 9 : doubled_num;
			} else {
				checkSum += cardNumber[index];
			}
		}
		for (int index = 0; index < n; index++) {
			sb.append(cardNumber[index]);
		}
		sb.append(10 - (checkSum % 10));
		return sb.toString();
	}

}
