package org.datum.dataproviders;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ResourceDataProvider implements DataProvider {
	
	private final Class<?> classLoader = getClass();

	private String name;

	public ResourceDataProvider(String name) {
		this.name = String.format("/data/%s", name);
	}

	@Override
	public Reader getReader() throws FileNotFoundException {
		return new InputStreamReader(classLoader.getResourceAsStream(name));
	}

}
