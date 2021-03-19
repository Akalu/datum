package org.datum.configuration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.datum.configuration.impl.SimpleConfigurator;
import org.datum.datasource.RandomDataSource;
import org.junit.Test;

public class ConfiguratorTest {
	
	@Test
	public void searchFunctionalTest() {
		SimpleConfigurator simpleConfigurator = new SimpleConfigurator("location:us");
		assertNotNull(simpleConfigurator);
		
		RandomDataSource found = simpleConfigurator.findBy("location:.*");
		assertNotNull(found);
		
		RandomDataSource allTries = simpleConfigurator.findBy(".*:TRIE");
		assertNotNull(found);
	}
	
	@Test
	public void testNotFound() {
		SimpleConfigurator simpleConfigurator = new SimpleConfigurator("persons");
		assertNotNull(simpleConfigurator);
		boolean caught = false;
		try {
			RandomDataSource found = simpleConfigurator.findBy("location:.*");
		} catch(IllegalArgumentException e) {
			caught = true;
		}
		assertTrue(caught);
		
	}

}
