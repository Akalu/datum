package org.datum.configuration.impl;

import org.datum.configuration.Configurator;
import org.datum.core.Pipeline;
import org.datum.datasource.DataProvider;
import org.datum.datasource.FieldSet;
import org.datum.datasource.impl.TrieDataSource;
import org.datum.datasource.model.DataSchema;
import org.datum.datasource.processor.Processor;
import org.datum.datasource.processor.impl.OrderConverter;
import org.datum.datasource.processor.impl.SimpleTypeConverter;
import org.datum.datasource.providers.ResourceDataProvider;
import org.datum.generator.GeneratorType;
import org.datum.util.CSVLoader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InMemoryConfigurator extends Configurator {

	private TrieDataSource locationDS;
	private String[] usLocationMergePath = new String[] { "state", "city", "zip_code", "area_codes", "latitude", "longitude"};
	private String[] caLocationMergePath = new String[] { "province", "city", "zip_code" };

	public InMemoryConfigurator() throws Exception {
		
		DataSchema schema = new DataSchema(6);
		schema.addType("state", "java.lang.String", 0);
		schema.addType("city", "java.lang.String", 1);
		schema.addType("zip_code", "java.lang.String", 2);
		schema.addType("area_codes", "java.lang.String", 3);
		schema.addType("latitude", "java.lang.Double", 4);
		schema.addType("longitude", "java.lang.Double", 5);

		Processor<FieldSet> typeConverter = new SimpleTypeConverter(schema);
		Processor<FieldSet> orderConverter = new OrderConverter(usLocationMergePath);

		DataProvider rdp = new ResourceDataProvider("us_locations.csv");
		CSVLoader loader = new CSVLoader();


		locationDS = new TrieDataSource("us_locations", schema);


		Pipeline<FieldSet> pipe = new Pipeline.Builder<FieldSet>()
				.addConsumer(locationDS)
				.addProcessor(typeConverter)
				.addProcessor(orderConverter)
				.build();

		loader.loadCSV(rdp, schema, pipe, true);
		log.info("datasource {} updated {} times", locationDS.getName(), locationDS.getCounter());
		log.info("unique nodes {}", locationDS.countNodes());

		register("location:us", GeneratorType.TRIE, locationDS);
	}

}
