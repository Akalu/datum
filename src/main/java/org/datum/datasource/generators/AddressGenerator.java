package org.datum.datasource.generators;

import static org.datum.datasource.generators.CommonGenerator.*;

/**
 * Used to generate a fictitiuos address using a simple combination algorithm
 * 
 * @author akaliutau
 *
 */
public class AddressGenerator {
	
	private final static String[] geoLocationType = {"Avenue", "Street", "Plaza", "Boulevard"};
	
	private final static String template_1 = "%d %s %s";

	public static String generateAddressLine1 (String seedWords){
		return String.format(template_1, random.nextInt(200) + 1, seedWords, getAny(geoLocationType));
	}
}
