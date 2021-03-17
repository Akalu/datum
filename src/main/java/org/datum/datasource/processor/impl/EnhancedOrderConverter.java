package org.datum.datasource.processor.impl;

import org.datum.datasource.FieldSet;
import org.datum.datasource.model.OrderedTypedFieldSet;
import org.datum.datasource.model.UnorderedTypedFieldSet;
import org.datum.datasource.processor.Processor;

/**
 * 
 * Used to convert unordered set to ordered one
 * Basically this is just a subprocess for re-arrangement of elements
 * 
 * This implementation does not re-order elements at all
 * 
 * @author akaliutau
 *
 */
public class EnhancedOrderConverter implements Processor<FieldSet> {

	private String[] mergePath;

	public EnhancedOrderConverter(String[] mergePath) {
		this.mergePath = mergePath;
	}

	@Override
	public FieldSet proceed(FieldSet t) {
		UnorderedTypedFieldSet fields = (UnorderedTypedFieldSet) t;

		OrderedTypedFieldSet res = new OrderedTypedFieldSet(fields.values());
		return res;
	}
}
