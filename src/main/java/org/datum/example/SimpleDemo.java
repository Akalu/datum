package org.datum.example;

import java.util.List;

import org.datum.configuration.impl.InMemoryConfigurator;
import org.datum.core.DataGenerator;
import org.datum.example.pojo.AddressPojo;
import org.datum.example.pojo.SimplePojo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleDemo {
	
	public static void main(String[] args) throws Exception {
		DataGenerator<AddressPojo> gen = new DataGenerator<>(new InMemoryConfigurator());
		gen.addGenerator(new AddressGenerator());
		AddressPojo pojo = gen.getOne();
		List<AddressPojo> pojoLi = gen.getBatch(5);
		log.info(pojo.toString());
		log.info(pojoLi.toString());
		
		
	}
}
