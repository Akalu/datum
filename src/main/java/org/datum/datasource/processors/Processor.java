package org.datum.datasource.processors;

public interface Processor<T> {
	T proceed(T t);
}
