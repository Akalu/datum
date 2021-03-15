package org.datum.datasource.processor.impl;

import org.datum.datasource.FieldSet;
import org.datum.datasource.model.DataSchema;
import org.datum.datasource.model.UnorderedStringFieldSet;
import org.datum.datasource.model.UnorderedTypedFieldSet;
import org.datum.datasource.processor.ConverterFactory;
import org.datum.datasource.processor.Processor;

/**
 * 
 * Type converter for string-based tuple
 * 
 * Used a schema for data conversion
 * 
 * Note: this is a simple type converter, which neither change the length of vector nor performs grouping of fields
 * (f.e. GPS coords can be transformed into special aggregating object)  
 * 
 * @author akaliutau
 *
 */
public class SimpleTypeConverter implements Processor<FieldSet> {

	private DataSchema schema;

	public SimpleTypeConverter(DataSchema schema) {
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
