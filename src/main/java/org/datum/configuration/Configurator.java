package org.datum.configuration;

import java.util.HashMap;
import java.util.Map;

import org.datum.datasource.RandomDataSource;
import org.datum.generator.GeneratorType;

public abstract class Configurator {

	protected final Map<String, RandomDataSource> sources = new HashMap<>();

	public RandomDataSource findBy(String source, GeneratorType type) {
		String key = String.format("%s:%s", source, type);
		if (!sources.containsKey(key)) {
			throw new IllegalArgumentException("Cannot figure out the RandomDataSource with key " + key);
		}
		return sources.get(key);
	}

	public void register(String label, RandomDataSource datatsource) {
		sources.put(label, datatsource);

	}

}
