package org.datum.datastructures;

import lombok.ToString;

@ToString
public class UnorderedTypedFieldSet extends FieldSet {

	private static final long serialVersionUID = 2L;

	private final Object[] arr;

	public UnorderedTypedFieldSet(int size) {
		this.arr = new Object[size];
	}

	public Object[] values() {
		return arr == null ? new Object[] {} : arr;
	}

	public Object value(int i) {
		return arr == null ? null : arr[i];
	}

	public void set(int i, Object o) {
		arr[i] = o;
	}

	@Override
	public int size() {
		return arr == null ? 0 : arr.length;
	}

}
