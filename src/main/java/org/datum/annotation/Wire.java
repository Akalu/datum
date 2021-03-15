package org.datum.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.datum.generator.GeneratorType;
import org.datum.generator.NameGeneratorType;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Wire {
	GeneratorType[] type() default {};

	String source() default "";
}
