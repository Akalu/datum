package org.datum.datasource.generators;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonGeneratorTest {
	
	@Test
	public void testGetRandomDate() {
		LocalDate date = CommonGenerator.getRandomDate(10);
		assertNotNull(date);
	}
	
	@Test
	public void testGetRandomDateWrongInput() {
		LocalDate date = CommonGenerator.getRandomDate(0);
		assertNotNull(date);
	}
	
	@Test
	public void testGetAny() {
		String rnd = CommonGenerator.getAny(new String[] {"one", "two"});
		assertNotNull(rnd);
	}

	@Test
	public void testGetAnyEmptyArray() {
		String rnd = CommonGenerator.getAny(new String[] {});
		assertNull(rnd);
	}
	
	@Test
	public void testDistributedProbability1() {
		String[] arr = {"first", "third", "second"};
		double[] probability = {0.1d, 0.6d, 0.3d};
		Map<String, Integer> stat = new HashMap<>();
		for (int i = 0; i < 1000; i++) {
			String val = CommonGenerator.getAnyDistributed(arr, probability);
			stat.compute(val, (k,v) -> v == null ? 1 : v + 1);
		}
		log.debug("statistics: {}", stat);
	}
	
	@Test
	public void testDistributedProbability2() {
		String[] arr = {"first", "third", "second", "fourth"};
		double[] probability = {0.1d, 0.3d, 0.1d, 0.5d};
		Map<String, Integer> stat = new HashMap<>();
		for (int i = 0; i < 1000; i++) {
			String val = CommonGenerator.getAnyDistributed(arr, probability);
			stat.compute(val, (k,v) -> v == null ? 1 : v + 1);
		}
		log.debug("statistics: {}", stat);
	}



}
