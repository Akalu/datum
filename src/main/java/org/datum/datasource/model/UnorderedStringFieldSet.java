package org.datum.datasource.model;

import java.util.Map;

import org.datum.datasource.FieldSet;

public class UnorderedStringFieldSet extends FieldSet {

	private static final long serialVersionUID = 1L;

	private final String[] arr;

	public UnorderedStringFieldSet(String[] arr) {
		this.arr = arr;
	}

	public String value(String key, Map<String, Integer> indexMap) {
		return arr[indexMap.get(key)];
	}

	public String[] values() {
		return arr == null ? new String[] {} : arr;
	}

	public String value(int i) {
		return arr == null ? null : arr[i];
	}

	@Override
	public int size() {
		return arr == null ? 0 : arr.length;
	}

}
