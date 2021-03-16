package org.datum.datasource.impl;

import java.util.HashMap;
import java.util.Map;

import org.datum.datasource.RandomDataSource;

/**
 * 
 * Used to generate surnames
 * @author akaliutau
 *
 */
public class NameDataSource implements RandomDataSource{

	@Override
	public Map<String, Object> getData() {
		Map<String, Object> res = new HashMap<>();
		res.put("last_name", LastNameGenerator.generateNameMarkovChainAlgorithm1());
		return res;
	}

	@Override
	public String name() {
		return "Last name custom generator";
	}

}
