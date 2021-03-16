package org.datum.example;

import java.util.Map;

import org.datum.annotation.Wire;
import org.datum.datasource.RandomDataSource;
import org.datum.example.pojo.SimplePojo;
import org.datum.generator.Generator;
import org.datum.generator.GeneratorType;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class PersonalDataGenerator extends Generator<SimplePojo> {
	
	@Wire(source="person_data", type=GeneratorType.TRIE)
	private RandomDataSource personaldataSource1;

	@Wire(source="person_data", type=GeneratorType.LAST_NAME)
	private RandomDataSource personaldataSource2;


	@Override
	public SimplePojo genData() {
		
		SimplePojo pojo = new SimplePojo();
		Map<String, Object> personal1 = personaldataSource1.getData();
		set(pojo::setFirstName, "first_name", personal1);
		Map<String, Object> personal2 = personaldataSource2.getData();
		set(pojo::setLastName, "last_name", personal2);
		return pojo;
	}
	
}
