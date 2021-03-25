package org.datum.datasource.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import org.datum.datasource.RandomDataSource;
import org.datum.datastructures.DataSchema;
import org.datum.datastructures.FieldSet;
import org.datum.datastructures.OrderedTypedFieldSet;
import org.datum.datastructures.TrieNode;
import org.datum.processor.DataConsumer;

import lombok.Getter;

/**
 * The generic structure useful for data of any type/origin
 * 
 * The source of unstructured data organized as a trie
 * 
 * @author akaliutau
 */
public class TrieDataSource implements RandomDataSource, DataConsumer<FieldSet> {

	@Getter
	private final String name;

	@Getter
	private final TrieNode root;

	private final DataSchema schema;

	private long nodes = 0L;
	private boolean nodesCounted = false;

	private long counter = 0L;

	public TrieDataSource(String name, DataSchema schema) {
		this.name = name;
		this.schema = schema;
		this.root = new TrieNode(name);
	}

	/**
	 * Insert data into trie
	 * 
	 * NOTE: the order of keys in Map is important, as it defines the mergePath
	 * 
	 * @param record
	 */
	@Override
	public void insert(FieldSet record) {
		OrderedTypedFieldSet typed = (OrderedTypedFieldSet) record;
		TrieNode node = root;
		nodesCounted = false;
		counter++;

		for (int i = 0; i < typed.size(); i++) {
			node = node.putIfAbsent(schema.getName(i), typed.value(i));
		}

	}

	/**
	 * Traverse trie down to leaf and collect all data on the way Null safe - always
	 * returns none-null map
	 */
	@Override
	public Map<String, Object> getData() {
		Map<String, Object> ret = new HashMap<>();
		TrieNode node = root;
		int idx = 0;
		while (node != null) {
			if (node != root) {
				ret.put(schema.getName(idx++), node.getValue());
			}
			node = node.getRandomNode();
		}
		return ret;
	}

	public long countNodes() {
		if (nodesCounted) {
			return nodes;
		}
		nodes = 0L;
		Queue<TrieNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			TrieNode curnode = q.poll();
			nodes += curnode.size();
			for (Entry<String, TrieNode> entry : curnode.getEntries()) {
				q.add(entry.getValue());
			}
		}
		nodesCounted = true;
		return nodes;
	}

	@Override
	public long getCounter() {
		return counter;
	}

	@Override
	public String name() {
		return this.name;
	}

}
