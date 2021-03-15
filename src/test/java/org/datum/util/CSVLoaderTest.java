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

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVLoaderTest {
	
	private String[] usLocationMergePath = new String[] { "country", "state", "city", "zip_code" };

	
	@Test
	public void functionalTestLoading() throws Exception {
		ResourceDataProvider rdp = new ResourceDataProvider("us_locations.csv");
		CSVLoader loader = new CSVLoader();
		DataSchema schema = new DataSchema(3);
		schema.addType("state", "java.lang.String", 0);
		schema.addType("city", "java.lang.String", 1);
		schema.addType("zip_code", "java.lang.String", 2);
		
		log.info("schema: {}", schema);
		
		Processor<FieldSet> typeConverter = new TypeConverter(schema);
		
		Pipeline<FieldSet> pipe = new Pipeline.Builder<FieldSet>()
				.addConsumer(new GenericConsumer())
				.addProcessor(typeConverter)
				.build();
		
		loader.loadCSV(rdp, schema , pipe, true);
	}
}
