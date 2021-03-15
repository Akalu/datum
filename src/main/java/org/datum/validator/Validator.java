package org.datum.validator;

public interface Validator<T> {
	default boolean valid(T t) {
		return true;
	}; 

	default boolean valid(T u, T v) {
		return true;
	}; 
}
