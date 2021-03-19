package org.datum.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import lombok.Getter;

public class TrieNode {
	
	public static final char separator = '=';
	public static final String key_template = "%s" + separator + "%s";
	
	@Getter
	private final Object value;

	private ConcurrentMap<String, TrieNode> children = null;

	public TrieNode(Object value) {
		this.value = value;
	}

	public TrieNode putIfAbsent(String nodeName, Object value) {
		if (children == null) {
			children = new ConcurrentHashMap<>();
		}
		String key = key(nodeName, value);
		return children.computeIfAbsent(key, n -> new TrieNode(value));
	}

	public boolean contains(String key) {
		return children == null ? false : children.containsKey(key);
	}

	public Set<Entry<String, TrieNode>> getEntries() {
		return children == null ? Set.of() : children.entrySet();
	}

	public TrieNode getNode(String key) {
		return children == null ? null : children.get(key);
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
		return value != null;
	}
	
	public int size() {
		return children == null ? 0 : children.size();
	}
	
	private String key(String name, Object value) {
		return String.format(key_template, name, value);
	}


}
