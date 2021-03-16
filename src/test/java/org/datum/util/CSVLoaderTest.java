package org.datum.util;

import org.datum.core.Pipeline;
import org.datum.datasource.FieldSet;
import org.datum.datasource.impl.GenericDataSource;
import org.datum.datasource.model.DataSchema;
import org.datum.datasource.processor.Processor;
import org.datum.datasource.processor.impl.SimpleTypeConverter;
import org.datum.datasource.providers.ResourceDataProvider;
import org.datum.util.CSVLoader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVLoaderTest {
	
	private String[] usLocationMergePath = new String[] { "state", "city", "zip_code", "area_codes", "latitude", "longitude"};

	
	@Test
	public void functionalTestLoading() throws Exception {

		DataSchema schema = new DataSchema(6);
		schema.addType("state", "java.lang.String", 0);
		schema.addType("city", "java.lang.String", 1);
		schema.addType("zip_code", "java.lang.String", 2);
		schema.addType("area_codes", "java.lang.String", 3);
		schema.addType("latitude", "java.lang.Double", 4);
		schema.addType("longitude", "java.lang.Double", 5);

		Processor<FieldSet> typeConverter = new SimpleTypeConverter(schema);

		ResourceDataProvider rdp = new ResourceDataProvider("us_locations.csv");
		CSVLoader loader = new CSVLoader();
		
		log.info("schema: {}", schema);
		
		GenericDataSource consumer = new GenericDataSource(); 
		Pipeline<FieldSet> pipe = new Pipeline.Builder<FieldSet>()
				.addConsumer(consumer)
				.addProcessor(typeConverter)
				.build();
		
		loader.loadCSV(rdp, schema , pipe, true);
		assertEquals(1000, consumer.getCounter());
	}
	
	@Test
	public void functionalPerformanceTest() throws Exception {

		DataSchema schema = new DataSchema(2);
		schema.addType("gender", "java.lang.String", 0);
		schema.addType("first_name", "java.lang.String", 1);

		Processor<FieldSet> typeConverter = new SimpleTypeConverter(schema);

		ResourceDataProvider rdp = new ResourceDataProvider("names.csv");
		CSVLoader loader = new CSVLoader();
		
		log.info("schema: {}", schema);
		
		GenericDataSource consumer = new GenericDataSource(); 
		Pipeline<FieldSet> pipe = new Pipeline.Builder<FieldSet>()
				.addConsumer(consumer)
				.addProcessor(typeConverter)
				.build();
		
		loader.loadCSV(rdp, schema , pipe, true);
		assertEquals(1000, consumer.getCounter());
	}

}
