package org.datum.datastructures;

public class OrderedTypedFieldSet extends FieldSet {
	private static final long serialVersionUID = 3L;

	private final Object[] arr;

	public OrderedTypedFieldSet(int size) {
		this.arr = new Object[size];
	}

	public OrderedTypedFieldSet(Object[] arr) {
		this.arr = arr;
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
