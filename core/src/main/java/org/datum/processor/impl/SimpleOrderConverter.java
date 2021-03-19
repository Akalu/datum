package org.datum.processor.impl;

import org.datum.datastructures.FieldSet;
import org.datum.datastructures.OrderedTypedFieldSet;
import org.datum.datastructures.UnorderedTypedFieldSet;
import org.datum.processor.Processor;

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
public class SimpleOrderConverter implements Processor<FieldSet> {

	private String[] mergePath;

	public SimpleOrderConverter(String[] mergePath) {
		this.mergePath = mergePath;
	}

	@Override
	public FieldSet proceed(FieldSet t) {
		UnorderedTypedFieldSet fields = (UnorderedTypedFieldSet) t;

		OrderedTypedFieldSet res = new OrderedTypedFieldSet(fields.values());
		return res;
	}
}
