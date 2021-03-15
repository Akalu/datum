package org.datum.datasource.generic;

import org.datum.core.DataConsumer;
import org.datum.datasource.FieldSet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericConsumer implements DataConsumer<FieldSet> {

	@Override
	public void insert(FieldSet record) {
		// do nothing - used for testing
		log.info("consume {}", record);
	}

}
