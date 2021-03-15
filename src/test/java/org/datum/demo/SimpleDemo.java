package org.datum.demo;

import java.util.List;

import org.datum.configuration.impl.InMemoryConfigurator;
import org.datum.core.DataGenerator;
import org.datum.pojo.SimplePojo;

public class SimpleDemo {
	
	public static void main(String[] args) throws Exception {
		DataGenerator<SimplePojo> gen = new DataGenerator<>(new InMemoryConfigurator());
		gen.addGenerator(new AddressGenerator());
		SimplePojo pojo = gen.getOne();
		List<SimplePojo> pojoLi = gen.getBatch(100);
		
	}
}
