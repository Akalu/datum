package org.datum.datasource;

import java.io.FileNotFoundException;
import java.io.Reader;

public interface DataProvider {
	Reader getReader() throws FileNotFoundException;
}
