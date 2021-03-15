package org.datum.demo;

import java.util.Map;

import org.datum.annotation.Wire;
import org.datum.datasource.RandomDataSource;
import org.datum.generator.Generator;
import org.datum.generator.GeneratorType;
import org.datum.pojo.AddressPojo;

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
public class AddressGenerator extends Generator<AddressPojo> {
	
	@Wire(source="location:.*", type=GeneratorType.TRIE)
	private RandomDataSource locations;
	

	@Override
	public AddressPojo setData() {
		AddressPojo pojo = new AddressPojo();
		Map<String, Object> location = locations.getData();
		set(pojo::setCity, "city", location);
		set(pojo::setState, "state", location);
		set(pojo::setZipCode, "zip_code", location);
		return pojo;
	}
	
}
