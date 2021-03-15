package org.datum.datasource.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Map;

import org.datum.util.DataUtil;
import org.datum.util.DebugUtil;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrieDataSourceTest {
	
	public static final DataSchema schema = DataUtil.getSampleSchemaLoc();
	
	@Test
	public void functionalTestTrie() {
		TrieDataSource trie = new TrieDataSource("test", schema);
		OrderedTypedFieldSet recordOne = new OrderedTypedFieldSet(3);
		recordOne.set(0, "CA");
		recordOne.set(1, "San Ramon");
		recordOne.set(2, "34534");
		trie.insert(recordOne);

		OrderedTypedFieldSet recordTwo = new OrderedTypedFieldSet(3);
		recordTwo.set(0, "CA");
		recordTwo.set(1, "Los Angeles");
		recordTwo.set(2, "55524");
		trie.insert(recordTwo);
		log.debug("trie {}", DebugUtil.getTrieAsList(trie));
		log.debug("random data {}", trie.getData());
	}
	
	@Test
	public void testDataExtraction() {
		TrieDataSource trie = new TrieDataSource("test", schema);
		OrderedTypedFieldSet recordOne = new OrderedTypedFieldSet(3);
		recordOne.set(0, "CA");
		recordOne.set(1, "San Ramon");
		recordOne.set(2, "34534");
		trie.insert(recordOne);
		
	}
	
	@Test
	public void testEmptyTrie() {
		TrieDataSource trie = new TrieDataSource("test", schema);
		OrderedTypedFieldSet recordOne = new OrderedTypedFieldSet(3);
		recordOne.set(0, "CA");
		recordOne.set(1, "San Ramon");
		recordOne.set(2, "34534");
		trie.insert(recordOne);
		
		Map<String, Object> data = trie.getData();
		assertNotNull(data);
		assertEquals(3, data.size());
		assertEquals(3, trie.countNodes());

	}
	
	@Test
	public void testPartialRecord() {
		TrieDataSource trie = new TrieDataSource("test", schema);
		OrderedTypedFieldSet recordOne = new OrderedTypedFieldSet(3);
		recordOne.set(0, "CA");
		recordOne.set(1, "No zip code city");
		trie.insert(recordOne);
		
		Map<String, Object> data = trie.getData();
		assertNotNull(data);
		assertEquals(2, data.size());
	}
}
