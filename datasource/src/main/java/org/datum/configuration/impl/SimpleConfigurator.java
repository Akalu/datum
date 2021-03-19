package org.datum.configuration.impl;

import org.datum.configuration.Configurator;
import org.datum.datasource.GeneratorType;
import org.datum.datasource.impl.TrieDataSource;
import org.datum.datastructures.DataSchema;

import lombok.extern.slf4j.Slf4j;

/**
 * Added for testing
 * 
 * @author akaliutau
 *
 */
@Slf4j
public class SimpleConfigurator extends Configurator {

	private final String name;

	public SimpleConfigurator(String name) {
		this.name = name;
		TrieDataSource trieDS = new TrieDataSource(name, new DataSchema(0));
		this.register(name, GeneratorType.TRIE, trieDS);
	}
	
	
}
