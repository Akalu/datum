package org.datum.datasource.processors;

import org.datum.configuration.ConverterFactory;
import org.datum.datasource.FieldSet;
import org.datum.datasource.model.DataSchema;
import org.datum.datasource.model.UnorderedStringFieldSet;
import org.datum.datasource.model.UnorderedTypedFieldSet;

/**
 * 
 * Type converter for string-based tuple
 * 
 * Used a schema for data conversion
 * 
 * @author akaliutau
 *
 */
public class TypeConverter implements Processor<FieldSet> {

	private DataSchema schema;

	public TypeConverter(DataSchema schema) {
		this.schema = schema;
	}

	@Override
	public FieldSet proceed(FieldSet t) {
		UnorderedStringFieldSet fields = (UnorderedStringFieldSet) t;
		int n = fields.size();

		UnorderedTypedFieldSet res = new UnorderedTypedFieldSet(n);

		for (int i = 0; i < n; i++) {
			res.set(i, ConverterFactory.convert(fields.value(i), schema.getTypeOf(i)));
		}
		return res;
	}
}
