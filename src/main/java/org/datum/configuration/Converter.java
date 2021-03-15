package org.datum.configuration;

@FunctionalInterface
public interface Converter<T> {
	T convert(String o);
}
