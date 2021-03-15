package org.datum.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class ConverterFactory {

	private final static Map<String, Converter<?>> converters = new HashMap<>();

	static {
		converters.put(String.class.getName(), o -> o);
		converters.put(Integer.class.getName(), Integer::valueOf);
	}

	@SuppressWarnings("unchecked")
	public static <T> T convert(@Nullable String o, String className) {
		if (o == null) {
			return null;
		}
		if (!converters.containsKey(className)) {
			throw new IllegalArgumentException(
					"Cannot figure out the converter for type " + className + ", known names: " + converters.keySet());
		}
		return (T) converters.get(className).convert(o);
	}

}
