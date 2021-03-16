package org.datum.datasource.generators;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.datum.datasource.generators.CommonGenerator.*;

public class EmailGenerator {
	
	private static final String[] providers = {"@gmail.com", "@live.com", "@yahoo.com"};
	
	private final static RandomString randomString = new RandomString(3, new SecureRandom(), RandomString.digits);
	
	public static enum Algorithm {
		RANDOM_MAIL_ALG_1
	}
	
	/**
	 * Factory of available algorithms
	 * 
	 * @param algorithm
	 * @return
	 */

	private final static Map<Algorithm, Supplier<String>> algs = new HashMap<>();

	static {
		algs.put(Algorithm.RANDOM_MAIL_ALG_1, () -> generateMailAddressAlgorithm1());
	}

	public static String generateEmail(Algorithm algorithm) {
		return algs.get(algorithm).get();
	}

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



}
