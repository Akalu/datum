package org.datum.example;

import java.util.Map;

import org.datum.annotation.Wire;
import org.datum.datasource.RandomDataSource;
import org.datum.example.pojo.SimplePojo;
import org.datum.generator.Generator;
import org.datum.generator.GeneratorType;

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
public class PersonalDataGenerator extends Generator<SimplePojo> {
	
	@Wire(source="person_data", type=GeneratorType.TRIE)
	private RandomDataSource personaldataSource;
	

	@Override
	public SimplePojo setData() {
		
		SimplePojo pojo = new SimplePojo();
		Map<String, Object> personal = personaldataSource.getData();
		set(pojo::setFirstName, "first_name", personal);
		return pojo;
	}
	
}
