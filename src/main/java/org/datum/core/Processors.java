package org.datum.core;

import java.lang.annotation.Annotation;
import java.util.function.Consumer;

import org.datum.annotation.Wire;

/**
 * Collections of processors to work with metadata
 */
public class Processors {

	private Processors() {
		throw new IllegalStateException("Utility class");
	}

	@SuppressWarnings("unchecked")
	public static Consumer<Class<?>> processEntryAnnotation(Context context, Class<?> clazz) {
		return cls -> {
			if (clazz.isAssignableFrom(cls) && cls.isAnnotationPresent(Wire.class)) {
				Annotation annotation = cls.getAnnotation(Wire.class);
				Wire injestDetails = (Wire) annotation;
				// do smth if needed
			}
		};
	}

}
