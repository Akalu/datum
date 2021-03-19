package org.datum.datastructures;

import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

/**
 * 
 * Used to hold metadata about fields' types in CSV for conversion of source
 * type (typically String) to target type
 * 
 * @author akaliutau
 *
 */
@ToString
public class DataSchema {

	private final Map<String, Integer> indexMap = new HashMap<>();

	private final String[] headerLine;
	private final String[] typeMap;

	public DataSchema(int size) {
		this.headerLine = new String[size];
		this.typeMap = new String[size];
	}

	public void addType(String fieldName, String className, int pos) {
		headerLine[pos] = fieldName;
		typeMap[pos] = className;
		indexMap.put(fieldName, pos);
	}

	public String getName(int index) {
		return headerLine[index];
	}

	public String getTypeOf(int index) {
		return typeMap[index];
	}

	public int getIndexOf(String fieldName) {
		return indexMap.get(fieldName);
	}

	public int size() {
		return headerLine == null ? 0 : headerLine.length;
	}
}
