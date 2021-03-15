package org.datum.configuration.impl;

import org.datum.configuration.Configurator;
import org.datum.datasource.model.DataSchema;
import org.datum.datasource.model.TrieDataSource;
import org.datum.generator.GeneratorType;

import lombok.extern.slf4j.Slf4j;

/**
 * Added just for testing
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
