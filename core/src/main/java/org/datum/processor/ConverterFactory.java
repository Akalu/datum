package org.datum.processor;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import org.datum.model.Gender;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ConverterFactory {

	private final static Map<String, Converter<?>> converters = new HashMap<>();

	static {
		converters.put(String.class.getName(), o -> o);
		converters.put(Integer.class.getName(), Integer::valueOf);
		converters.put(Long.class.getName(), Long::valueOf);
		converters.put(Double.class.getName(), Double::valueOf);
		converters.put(Gender.class.getName(), Gender::valueOf);
	}

	@SuppressWarnings("unchecked")
	public static <T> T convert(@Nullable String o, String className) {
		if (o == null) {
			return null;
		}
		if (converters.containsKey(className)) {
			return (T) converters.get(className).convert(o);
		}		
		return fromJson(o);
	}
	
	public static <T> T fromJson(String json){
		GsonBuilder gson = new GsonBuilder();
	    Type collectionType = new TypeToken<T>(){}.getType();

	    T obj = gson.create().fromJson(json, collectionType);
		return obj;
	}

}
