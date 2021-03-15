package org.datum.datasource.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import lombok.Getter;

public class TrieNode {
	@Getter
	private final String name;

	@Getter
	private final Object value;

	private ConcurrentMap<String, TrieNode> children = null;

	public TrieNode(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public TrieNode putIfAbsent(String nodeName, Object value) {
		return children.computeIfAbsent(nodeName, n -> new TrieNode(nodeName, value));
	}

	public boolean contains(String nodeName) {
		return children == null ? false : children.containsKey(nodeName);
	}

	public Set<Entry<String, TrieNode>> getEntries() {
		return children == null ? Set.of() : children.entrySet();
	}

	public TrieNode getNode(String nodeName) {
		return children == null ? null : children.get(nodeName);
	}

	public TrieNode getRandomNode() {
		if (children == null || children.isEmpty()) {
			return null;
		}
		List<String> keys = new ArrayList<>(children.keySet());
		Random rand = new Random();
		return children.get(keys.get(rand.nextInt(keys.size())));
	}

	public boolean hasValue() {
		return value == null;
	}

}
