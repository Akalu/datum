package org.datum.datasource.generators;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.datum.datasource.generators.RandomGeneratorUtils.*;

import static org.datum.datasource.generators.Algorithm.*;

/**
 * Random email generator
 * @author akaliutau
 *
 */
public class EmailGenerator {
	
	private static final String emailShortTemplate = "%s%s%s";
	private static final String emailLongTemplate = "%s.%s%s";
	
	private static final String[] providers = {"@gmail.com", "@live.com", "@yahoo.com"};
	
	private final static RandomString randomString = new RandomString(3, new SecureRandom(), RandomString.digits);
	
	/**
	 * Factory of available algorithms
	 * 
	 * @param algorithm
	 * @return
	 */

	private final static Map<Algorithm, Supplier<String>> algs = new HashMap<>();

	static {
		algs.put(EMAIL_RANDOM_1, () -> generateMailAddressAlgorithm1());
	}
	
	private final static Map<Algorithm, Function<String[], ?>> parametricAlgs = new HashMap<>();

	static {
		parametricAlgs.put(EMAIL_SHORT, (args) -> generateShortMailAddressFromName(args));
		parametricAlgs.put(EMAIL_LONG, (args) -> generateLongMailAddressFromName(args));
	}


	public static String generateEmail(Algorithm algorithm) {
		return algs.get(algorithm).get();
	}

	public static String generateEmail(Algorithm algorithm, String... params) {
		algorithm.validate(params.length);
		return algs.get(algorithm).get();
	}

	/**
	 * Algorithm 1 generates emails like daru123@gmail.com (fixed length, mix of letters and digits, letters go first)
	 * 
	 * @return
	 */
	private static String generateMailAddressAlgorithm1() {
		StringBuilder sb = new StringBuilder();
		sb.append(getAny(consonants));
		sb.append(getAny(vowels));
		sb.append(getAny(consonants));
		sb.append(getAny(vowels));
		sb.append(randomString.nextString());
		sb.append(getAny(providers));
		return sb.toString();
	}

	private static String generateShortMailAddressFromName(String[] names) {
		return String.format(emailShortTemplate, names[0].charAt(0), names[1], getAny(providers)).toLowerCase();
	}


	private static String generateLongMailAddressFromName(String[] names) {
		return String.format(emailLongTemplate, names[0].charAt(0), names[1], getAny(providers)).toLowerCase();
	}

}
