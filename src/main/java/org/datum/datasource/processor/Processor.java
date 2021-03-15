package org.datum.datasource.processor;

public interface Processor<T> {
	T proceed(T t);
}
