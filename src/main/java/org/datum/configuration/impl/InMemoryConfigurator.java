package org.datum.configuration.impl;

import org.datum.configuration.Configurator;
import org.datum.core.Pipeline;
import org.datum.datasource.DataProvider;
import org.datum.datasource.FieldSet;
import org.datum.datasource.model.DataSchema;
import org.datum.datasource.model.TrieDataSource;
import org.datum.datasource.processors.OrderConverter;
import org.datum.datasource.processors.Processor;
import org.datum.datasource.processors.TypeConverter;
import org.datum.datasource.providers.ResourceDataProvider;
import org.datum.util.CSVLoader;

public class InMemoryConfigurator extends Configurator {

	private TrieDataSource locationDS;
	private String[] usLocationMergePath = new String[] { "country", "state", "city", "zip_code" };
	private String[] caLocationMergePath = new String[] { "country", "province", "city", "zip_code" };

	public InMemoryConfigurator() throws Exception {

		DataSchema schema = null;
		locationDS = new TrieDataSource("locations", schema);

		Processor<FieldSet> typeConverter = new TypeConverter(schema);
		Processor<FieldSet> orderConverter = new OrderConverter(usLocationMergePath);

		Pipeline<FieldSet> pipe = new Pipeline.Builder<FieldSet>().addConsumer(locationDS).addProcessor(typeConverter)
				.addProcessor(orderConverter).build();

		CSVLoader loader = new CSVLoader();
		DataProvider provider = new ResourceDataProvider("us_locations.csv");
		loader.loadCSV(provider, schema, pipe, true);

		register("locations:LOCATION", locationDS);
	}

}
