package org.datum.generator;

import java.util.Map;
import java.util.function.Consumer;

public abstract class Generator<T> {

	@SuppressWarnings("unchecked")
	protected <T> void set(Consumer<T> setter, String key, Map<String, Object> location) {
		setter.accept((T) location.get(key));
	}

	public abstract T setData(T pojo);
}
