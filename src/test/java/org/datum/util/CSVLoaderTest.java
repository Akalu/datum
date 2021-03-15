package org.datum.util;

import org.datum.core.Pipeline;
import org.datum.datasource.FieldSet;
import org.datum.datasource.generic.GenericConsumer;
import org.datum.datasource.model.DataSchema;
import org.datum.datasource.processors.Processor;
import org.datum.datasource.processors.TypeConverter;
import org.datum.datasource.providers.ResourceDataProvider;
import org.datum.util.CSVLoader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVLoaderTest {
	
	private String[] usLocationMergePath = new String[] { "state", "city", "zip_code" };

	
	@Test
	public void functionalTestLoading() throws Exception {

		DataSchema schema = new DataSchema(3);
		schema.addType("state", "java.lang.String", 0);
		schema.addType("city", "java.lang.String", 1);
		schema.addType("zip_code", "java.lang.String", 2);

		Processor<FieldSet> typeConverter = new TypeConverter(schema);

		ResourceDataProvider rdp = new ResourceDataProvider("us_locations.csv");
		CSVLoader loader = new CSVLoader();
		
		log.info("schema: {}", schema);
		
		GenericConsumer consumer = new GenericConsumer(); 
		Pipeline<FieldSet> pipe = new Pipeline.Builder<FieldSet>()
				.addConsumer(consumer)
				.addProcessor(typeConverter)
				.build();
		
		loader.loadCSV(rdp, schema , pipe, true);
		assertEquals(2, consumer.getCounter());
	}
}
