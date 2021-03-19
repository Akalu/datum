package org.datum.datasource.impl;

import static org.datum.generators.RandomGeneratorUtils.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.datum.dataproviders.DataProvider;
import org.datum.datasource.RandomDataSource;
import org.datum.generators.AddressGenerator;
import org.datum.util.CheckPoint;

import lombok.extern.slf4j.Slf4j;

/**
 * A linear data source to generate random strings using a simple combination
 * algorithm
 * 
 * @author akaliutau
 *
 */
@Slf4j
public class AddressDataSource implements RandomDataSource {

	private String[] words = null;

	public AddressDataSource(DataProvider dataProvider) throws FileNotFoundException, IOException {
		CheckPoint t = new CheckPoint();
		List<String> keyWords = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(dataProvider.getReader())) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				keyWords.add(line);
			}
		}
		words = keyWords.toArray(new String[keyWords.size()]);
		log.debug("loaded {} words", words.length);
		t.end();
	}

	@Override
	public Map<String, Object> getData() {
		Map<String, Object> res = new HashMap<>();
		String seedWords = getAny(words);
		res.put("address_line_1", AddressGenerator.generateAddressLine1(seedWords));
		return res;
	}

	@Override
	public String name() {
		return "Address line custom generator";
	}

}
