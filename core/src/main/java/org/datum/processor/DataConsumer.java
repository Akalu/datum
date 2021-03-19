package org.datum.processor;

public interface DataConsumer<T> {
	void insert(T record);

	long getCounter();

}
