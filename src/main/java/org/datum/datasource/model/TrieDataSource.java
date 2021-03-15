package org.datum.datasource.model;

import java.util.HashMap;
import java.util.Map;

import org.datum.core.DataConsumer;
import org.datum.datasource.FieldSet;
import org.datum.datasource.RandomDataSource;

import lombok.Getter;

/**
 * 
 * The source of unstructured data organized as a trie
 * 
 * @author akaliutau
 */
public class TrieDataSource implements RandomDataSource, DataConsumer<FieldSet> {

	@Getter
	private final String name;

	private final TrieNode root;

	private final DataSchema schema;

	public TrieDataSource(String name, DataSchema schema) {
		this.name = name;
		this.schema = schema;
		this.root = new TrieNode(name, null);
	}

	/**
	 * insert data into trie 
	 * 
	 * NOTE: the order of keys in Map is important, as it
	 * defines the mergePath
	 * 
	 * @param record
	 */
	@Override
	public void insert(FieldSet record) {
		OrderedTypedFieldSet typed = (OrderedTypedFieldSet) record;
		TrieNode node = root.putIfAbsent("locations", null);

		for (int i = 0; i < typed.size(); i++) {
			node = node.putIfAbsent(schema.getName(i), typed.value(i));
		}

	}

	/**
	 * Traverse trie down to leaf and collect all data on the way
	 */
	@Override
	public Map<String, Object> getData() {
		Map<String, Object> ret = new HashMap<>();
		TrieNode node = root.getNode("locations");
		while (node != null) {
			if (node.hasValue()) {
				ret.put(node.getName(), node.getValue());
			}
			node = node.getRandomNode();
		}
		return ret;
	}

}
