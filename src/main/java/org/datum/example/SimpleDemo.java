package org.datum.example;

import java.util.List;

import org.datum.configuration.Configurator;
import org.datum.configuration.impl.InMemoryConfigurator;
import org.datum.core.DataGenerator;
import org.datum.example.pojo.AddressPojo;
import org.datum.example.pojo.SimplePojo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleDemo {
	
	public static void main(String[] args) throws Exception {
		Configurator config = new InMemoryConfigurator();
		DataGenerator<AddressPojo> gen = new DataGenerator<>(config);
		gen.addGenerator(new AddressGenerator());
		AddressPojo a = gen.getOne();
		List<AddressPojo> aLi = gen.getBatch(5);
		log.info(a.toString());
		log.info(aLi.toString());

		DataGenerator<SimplePojo> persona = new DataGenerator<>(config);
		persona.addGenerator(new PersonalDataGenerator());
		SimplePojo p = persona.getOne();
		List<SimplePojo> pLi = persona.getBatch(15);
		log.info(p.toString());
		log.info(pLi.toString());

		
	}
}
