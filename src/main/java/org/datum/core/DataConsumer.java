package org.datum.core;

public interface DataConsumer<T> {
	void insert(T record);
}
