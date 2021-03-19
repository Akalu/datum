package org.datum.datasource.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.datum.datasource.RandomDataSource;
import org.datum.datasource.generators.CreditCardGenerator;
import org.datum.datasource.generators.EmailGenerator;
import org.datum.datasource.generators.LastNameGenerator;
import org.datum.model.Gender;

import static org.datum.datasource.generators.RandomGeneratorUtils.*;
import static org.datum.datasource.generators.Algorithm.*;
/**
 * 
 * Used to generate personal data (last name, email, credit card number, gender, etc)
 * @author akaliutau
 *
 */
public class PersonDataSource implements RandomDataSource{
	
	private static List<Double> prob = Arrays.stream(Gender.values()).map(g -> g.getProbability()).collect(Collectors.toList());
	
	private static double[] genderDistribution = null;
	
	static {
		genderDistribution = new double[prob.size()];
		for (int i = 0; i < prob.size(); i++) {
			genderDistribution[i] = prob.get(i);
		}
	}

	@Override
	public Map<String, Object> getData() {
		
		Map<String, Object> res = new HashMap<>();
		
		res.put("last_name", LastNameGenerator.generateName(LAST_NAME_MARKOV_CHAIN_1));
		res.put("email", EmailGenerator.generateEmail(EMAIL_RANDOM_1));
		res.put("credit_card", CreditCardGenerator.generateCard());
		res.put("gender", getAnyDistributed(Gender.values(), genderDistribution));
		res.put("age", getAnyInRange(18, 100));

		return res;
	}

	@Override
	public String name() {
		return "Personal data custom generator";
	}

}
