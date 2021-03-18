package org.datum.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.datum.datasource.RandomDataSource;
import org.datum.generator.GeneratorType;

public abstract class Configurator {

	public static final String ANY_TRIE = ".*:TRIE";
	public static final String ANY_LOCATION_TRIE = "location:.*:TRIE";

	protected final Map<String, RandomDataSource> sources = new HashMap<>();

	public RandomDataSource findBy(String regex) {
		Pattern pattern = Pattern.compile(regex);
		List<RandomDataSource> found = sources.entrySet()
				.stream()
				.filter(input -> pattern.matcher(input.getKey()).matches())
				.map(entry -> entry.getValue())
				.collect(Collectors.toList());

		if (found.isEmpty()) {
			throw new IllegalArgumentException("Cannot figure out the RandomDataSource with key " + regex);
		} else if (found.size() > 1) {
			throw new IllegalArgumentException("Found multiple RandomDataSource with key " + regex);
		}
		
		return found.get(0);
	}

	public void register(String label, GeneratorType type, RandomDataSource datasource) {
		sources.put(key(label, type), datasource);
	}

	private static String key(String label, GeneratorType type) {
		return String.format("%s:%s", label, type);
	}

}
