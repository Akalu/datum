package org.datum.datasource.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import org.datum.annotation.Wire;
import org.datum.configuration.Configurator;
import org.datum.datasource.Generator;
import org.datum.datasource.GeneratorType;
import org.datum.datasource.RandomDataSource;
import org.datum.reflection.ReflectionHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * Collections of processors to work with metadata
 */
@Slf4j
public class AnnotationProcessors {

	private AnnotationProcessors() {
		throw new IllegalStateException("Utility class");
	}

	public static void processWireAnnotation(Generator<?> target, Configurator configurator, Field field) {
		Class<?> cls = field.getType();
		log.debug("field: {}", field);
		log.debug("cls: {}", cls);
		log.debug("cls.isAssignableFrom(RandomDataSource.class): {}", cls.isAssignableFrom(RandomDataSource.class));
		if (cls.isAssignableFrom(RandomDataSource.class) && field.isAnnotationPresent(Wire.class)) {
			Annotation annotation = field.getAnnotation(Wire.class);
			Wire annoDetails = (Wire) annotation;
			log.debug("annoDetails: {}", annoDetails);
			String regex = String.format("%s:%s", annoDetails.source(), join(annoDetails.type()));
			RandomDataSource ds = configurator.findBy(regex);
			ReflectionHelper.setField(target, field, ds);
		}
	}

	private static String join(GeneratorType[] arr) {
		if (arr == null || arr.length == 0) {
			return ".*";
		}
		if (arr.length == 1) {
			return arr[0].toString();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (GeneratorType type : arr) {
			if (sb.length() > 0) {
				sb.append("|");
			}
			sb.append(type);
		}
		sb.append(")");
		return sb.toString();
	}

}
