package org.datum.processor.impl;

import org.datum.datastructures.DataSchema;
import org.datum.datastructures.FieldSet;
import org.datum.datastructures.UnorderedStringFieldSet;
import org.datum.datastructures.UnorderedTypedFieldSet;
import org.datum.processor.ConverterFactory;
import org.datum.processor.Processor;

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
