package org.datum.processor;

public interface Processor<T> {
	T proceed(T t);
}
