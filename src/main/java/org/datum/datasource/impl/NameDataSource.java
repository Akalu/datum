package org.datum.datasource.impl;

import java.util.HashMap;
import java.util.Map;

import org.datum.datasource.RandomDataSource;
import org.datum.datasource.generators.LastNameGenerator;

import static org.datum.datasource.generators.LastNameGenerator.Algorithm.*;

/**
 * 
 * Used to generate personal data (last name, email, credit card number, gender, etc)
 * @author akaliutau
 *
 */
public class NameDataSource implements RandomDataSource{

	@Override
	public Map<String, Object> getData() {
		Map<String, Object> res = new HashMap<>();
		res.put("last_name", LastNameGenerator.generateName(MARKOV_CHAIN_ALG_1));
		return res;
	}

	@Override
	public String name() {
		return "Last name custom generator";
	}

}
