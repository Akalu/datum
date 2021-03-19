package org.datum.generators;

import static org.datum.algorithm.Algorithm.PHONE_RANDOM_1;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.datum.algorithm.Algorithm;

/**
 * Random phone number generator
 * @author akaliutau
 *
 */
public class PhoneGenerator {
	
	private final static String template_1 = "+%d%s"; 

	private final static RandomString randomString = new RandomString(9, new SecureRandom(), RandomString.digits);

	/**
	 * Factory of available algorithms
	 * 
	 * @param algorithm
	 * @return
	 */

	private final static Map<Algorithm, Function<Integer,String>> algs = new HashMap<>();

	static {
		algs.put(PHONE_RANDOM_1, area -> generatePhoneNumberAlgorithm1(area));
	}

	private static String generatePhoneNumberAlgorithm1(int areaCode) {
		return String.format(template_1, areaCode, randomString.nextString());
	}
	
	
	public static String generatePhone(Algorithm algorithm, int... params) {
		algorithm.validate(params.length);
		return algs.get(algorithm).apply(params[0]);
	}

	

	
}
