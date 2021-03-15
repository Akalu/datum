package org.datum.demo;

import java.util.Map;

import org.datum.annotation.Wire;
import org.datum.datasource.RandomDataSource;
import org.datum.generator.Generator;
import org.datum.generator.GeneratorType;
import org.datum.pojo.SimplePojo;

/**
 * The engine analyzes and injects necessary dependencies, which include:
 * 
 *  1) Data sources
 *  
 *  2) Converter chains on the basis of POJO analysis
 *  
 *  
 * @author akaliutau
 *
 */
public class AddressGenerator extends Generator<SimplePojo> {
	
	@Wire(source="address", type=GeneratorType.RANDOM_ADDRESS_STRING)
	private RandomDataSource locationSource;

	@Wire(source="person_data", type=GeneratorType.TRIE)
	private RandomDataSource personSource;
	

	@Override
	public SimplePojo setData(SimplePojo pojo) {
		Map<String, Object> location = locationSource.getData();
		set(pojo::setAddress, "address line 1", location);

		Map<String, Object> personal = personSource.getData();
		set(pojo::setFirstName, "first_name", personal);
		return pojo;
	}
	
}
