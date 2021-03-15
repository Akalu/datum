package org.datum.datasource.processors;

import org.datum.datasource.FieldSet;
import org.datum.datasource.model.OrderedTypedFieldSet;
import org.datum.datasource.model.UnorderedTypedFieldSet;

public class OrderConverter implements Processor<FieldSet> {

	private String[] mergePath;

	public OrderConverter(String[] mergePath) {
		this.mergePath = mergePath;
	}

	@Override
	public FieldSet proceed(FieldSet t) {
		UnorderedTypedFieldSet fields = (UnorderedTypedFieldSet) t;

		OrderedTypedFieldSet res = new OrderedTypedFieldSet(fields.size());
		return res;
	}
}
