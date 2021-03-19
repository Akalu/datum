package org.datum.processor;

@FunctionalInterface
public interface Converter<T> {
	T convert(String o);
}
