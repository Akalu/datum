package org.datum.example;

import java.util.Map;

import org.datum.annotation.Wire;
import org.datum.datasource.Generator;
import org.datum.datasource.GeneratorType;
import org.datum.datasource.RandomDataSource;
import org.datum.example.pojo.SimplePojo;

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
	
	@Wire(source="names", type=GeneratorType.TRIE)
	private RandomDataSource names;

	@Wire(source="person_data", type=GeneratorType.PERSONAL_DATA)
	private RandomDataSource personalData;


	@Override
	public SimplePojo genData() {
		
		SimplePojo pojo = new SimplePojo();
		Map<String, Object> personal1 = names.getData();
		set(pojo::setFirstName, "first_name", personal1);
		set(pojo::setGender, "gender", personal1);
		
		Map<String, Object> personal2 = personalData.getData();
		set(pojo::setLastName, "last_name", personal2);
		set(pojo::setEmail, "email", personal2);
		set(pojo::setCreditCard, "credit_card", personal2);
		set(pojo::setAge, "age", personal2);
		return pojo;
	}
	
}
