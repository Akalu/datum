package org.datum.datasource;

import java.util.Map;

public interface RandomDataSource {
	Map<String, Object> getData();
	String name();
}
