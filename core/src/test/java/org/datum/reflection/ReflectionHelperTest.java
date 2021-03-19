package org.datum.reflection;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.datum.pojo.ComplexPojo;
import org.datum.pojo.Pojo;
import org.junit.Test;

public class ReflectionHelperTest {
	
	@Test
	public void functionalTest() {
		Pojo obj = new Pojo();
		List<Field> fields = ReflectionHelper.getAllFields(obj);
		assertEquals(3, fields.size());
	}
	
	@Test
	public void testSetFields() {
		Pojo obj = new Pojo();
		List<Field> fields = ReflectionHelper.getAllFields(obj);
		assertEquals(3, fields.size());
		for (Field f : fields) {
			if (f.getName().equals("age"))
				ReflectionHelper.setField(obj, f, Integer.valueOf(10));
			if (f.getName().equals("age"))
				ReflectionHelper.setField(obj, f, 10);
			if (f.getName().equals("name"))
				ReflectionHelper.setField(obj, f, "test name");
		}
		assertEquals(obj.getName(), "test name");
		assertEquals(obj.getAge(), 10);
		
	}
	
	@Test
	public void testNullValueToPrimitiveType() {
		Pojo obj = new Pojo();
		List<Field> fields = ReflectionHelper.getAllFields(obj);
		Optional<Field> field = fields.stream().filter(p -> p.getName().equals("age")).findAny();
		ReflectionHelper.setField(obj, field.get(), 10);
		ReflectionHelper.setField(obj, field.get(), null);
	}

	@Test
	public void testStaticFieldsShouldBeIgnored() {
		ComplexPojo obj = new ComplexPojo();
		List<Field> fields = ReflectionHelper.getAllFields(obj);
		assertEquals(3, fields.size());
		
	}

	@Test
	public void testEdgeCases() {
		List<Field> fields = ReflectionHelper.getAllFields(null);
		assertEquals(0, fields.size());
	}
	
}
