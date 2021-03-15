package org.datum.core;

import java.util.ArrayList;
import java.util.List;

import org.datum.configuration.Configurator;
import org.datum.generator.Generator;

/**
 * The core class implementing the main functionality and responsible for
 * run-time configuration
 * 
 * @author akaliutau
 *
 * @param <T>
 */
public class DataGenerator<T> {

	private final Configurator configurator;
	private final List<Generator<?>> generators = new ArrayList<>();

	public DataGenerator(Configurator configurator) {
		this.configurator = configurator;
	}

	/**
	 * Adds new generator to the list, injects necessary DataSources from
	 * configurator if needed
	 * 
	 * @param addressGenerator
	 */
	public void addGenerator(Generator<?> generator) {
		// TODO Auto-generated method stub

	}

	public T getOne() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getBatch(int count) {
		// TODO Auto-generated method stub
		return null;
	}
}
