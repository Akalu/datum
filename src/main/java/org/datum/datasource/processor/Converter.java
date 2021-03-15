package org.datum.datasource.processor;

@FunctionalInterface
public interface Converter<T> {
	T convert(String o);
}
