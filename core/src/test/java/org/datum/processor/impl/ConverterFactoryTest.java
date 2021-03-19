package org.datum.processor.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.datum.processor.ConverterFactory;
import org.junit.Test;

import com.google.gson.internal.LinkedTreeMap;

public class ConverterFactoryTest {
	
	@Test
	public void functionalTest() {
		String s1 = ConverterFactory.convert(null, "java.lang.String");
		assertNull(s1);
		String s2 = ConverterFactory.convert("test string", "java.lang.String");
		assertEquals("test string", s2);
		
		Long l1 = ConverterFactory.convert(null, "java.lang.Long");
		assertNull(l1);
		Long l2 = ConverterFactory.convert("123", "java.lang.Long");
		assertEquals(123L, l2.longValue());

	}
	
	@Test
	public void testGenericType() {
		boolean caught = false;
		Object o1 = null;
		try {
			o1 = ConverterFactory.convert("{}", "java.lang.Map");
		}catch(IllegalArgumentException e) {
			caught = true;
		}
		assertFalse(caught);
		assertNotNull(o1);
		assertEquals(LinkedTreeMap.class, o1.getClass());
	}
	
	@Test
	public void testWrongType() {
		boolean caught = false;
		Object o1 = null;
		try {
			o1 = ConverterFactory.convert("", "java.lang.Object");
		}catch(IllegalArgumentException e) {
			caught = true;
		}
		assertFalse(caught);
		assertNull(o1);
	}


}
