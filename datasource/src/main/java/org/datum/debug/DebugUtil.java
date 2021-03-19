package org.datum.debug;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.datum.datasource.impl.TrieDataSource;
import org.datum.datastructures.TrieNode;

import java.util.Map.Entry;

/**
 * Different tools for debugging and visualizations
 * 
 * @author akaliutau
 *
 */
public class DebugUtil {
	
	public static List<String> getTrieAsList(TrieDataSource trie){
		List<String> ret = new ArrayList<>();
		Queue<TrieNode> q = new LinkedList<>();
		q.add(trie.getRoot());

		while(!q.isEmpty()) {
			TrieNode curNode = q.poll();
			for (Entry<String, TrieNode> entry : curNode.getEntries()) {
				q.add(entry.getValue());
				ret.add(entry.getKey());

			}
		}
		return ret;
	}
	
	
}
