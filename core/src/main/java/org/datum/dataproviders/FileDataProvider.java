package org.datum.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class FileDataProvider implements DataProvider {

	private File path;

	public FileDataProvider(File path) {
		this.path = path;
	}

	@Override
	public Reader getReader() throws FileNotFoundException {
		return new FileReader(this.path);
	}
}
