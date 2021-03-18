package org.datum.datasource.generators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.datum.datasource.generators.LastNameGenerator;
import org.datum.util.CheckPoint;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

import static org.datum.datasource.generators.LastNameGenerator.Algorithm.*;

@Slf4j
public class LastNameGeneratorTest {

	@Test
	public void functionalTest() {
		String name1 = LastNameGenerator.generateName(MARKOV_CHAIN_ALG_1);
		log.debug("alg 1: {}", name1);

	}

	@Test
	public void performanceTest() {
		CheckPoint t1 = new CheckPoint();
		for (int i = 0; i < 10; i++) {
			String name1 = LastNameGenerator.generateName(MARKOV_CHAIN_ALG_1);
			log.debug("alg 1: {}", name1);
		}
		t1.end();

	}

	@Test
	public void testClean1() {
		StringBuilder sb = new StringBuilder();
		sb.append("orr");
		LastNameGenerator.clean(sb);
		assertEquals("or", sb.toString());
	}

	@Test
	public void testClean2() {
		StringBuilder sb = new StringBuilder();
		sb.append("vvw");
		LastNameGenerator.clean(sb);
		assertEquals("vw", sb.toString());
	}

	@Test
	public void testClean3() {
		StringBuilder sb = new StringBuilder();
		sb.append("zz");
		LastNameGenerator.clean(sb);
		assertEquals("z", sb.toString());
	}
	
	@Test
	public void testClean4() {
		StringBuilder sb = new StringBuilder();
		sb.append("ozzy");
		LastNameGenerator.clean(sb);
		assertEquals("ozy", sb.toString());
	}

	@Test
	public void testClean5() {
		StringBuilder sb = new StringBuilder();
		sb.append("zzoo");
		LastNameGenerator.clean(sb);
		assertEquals("zoo", sb.toString());
	}


	@Test
	public void testClean6() {
		StringBuilder sb = new StringBuilder();
		LastNameGenerator.clean(sb);
	}

	@Test
	public void testSetFirstToCapital1() {
		StringBuilder sb = new StringBuilder();
		sb.append("abcd");
		List<Integer> toCapitilize = Arrays.asList(0,2);
		LastNameGenerator.setFirstToCapital(sb, toCapitilize );
		assertEquals('A', sb.charAt(0));
		assertEquals('C', sb.charAt(2));
	}
	
	@Test
	public void testSetFirstToCapital2() {
		StringBuilder sb = new StringBuilder();
		sb.append("ab");
		List<Integer> toCapitilize = Arrays.asList(0,2);
		LastNameGenerator.setFirstToCapital(sb, toCapitilize );
		assertEquals('A', sb.charAt(0));
	}
	
	@Test
	public void testSetFirstToCapital3() {
		StringBuilder sb = new StringBuilder();
		List<Integer> toCapitilize = Arrays.asList(0,2);
		LastNameGenerator.setFirstToCapital(sb, toCapitilize);
		
	}
	
	@Test
	public void testContainsDoubleConsonants() {
		assertTrue(LastNameGenerator.containsDoubleConsonants("dfaag"));
		assertTrue(LastNameGenerator.containsDoubleConsonants("aadfaa"));
		assertTrue(LastNameGenerator.containsDoubleConsonants("dg"));
		assertTrue(LastNameGenerator.containsDoubleConsonants("adga"));
		
		assertFalse(LastNameGenerator.containsDoubleConsonants("aaa"));
		assertFalse(LastNameGenerator.containsDoubleConsonants("aa"));
		assertFalse(LastNameGenerator.containsDoubleConsonants("aaaaaaaaaaa"));
		assertFalse(LastNameGenerator.containsDoubleConsonants("dafaag"));
		assertFalse(LastNameGenerator.containsDoubleConsonants(""));
		assertFalse(LastNameGenerator.containsDoubleConsonants("dafasaga"));
	}


	@Test
	public void testContainsDoubleVowels() {
		assertTrue(LastNameGenerator.containsDoubleVowels("dfaag"));
		assertTrue(LastNameGenerator.containsDoubleVowels("aa"));
		assertTrue(LastNameGenerator.containsDoubleVowels("sadsdfsassaa"));
		assertTrue(LastNameGenerator.containsDoubleVowels("aaa"));
		assertTrue(LastNameGenerator.containsDoubleVowels("aaaa"));

		assertFalse(LastNameGenerator.containsDoubleVowels("dafag"));
		assertFalse(LastNameGenerator.containsDoubleVowels("adafag"));
		assertFalse(LastNameGenerator.containsDoubleVowels("a"));
		assertFalse(LastNameGenerator.containsDoubleVowels("da"));
	}


}
