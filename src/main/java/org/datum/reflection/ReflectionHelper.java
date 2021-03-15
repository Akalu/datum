package org.datum.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReflectionHelper {

	/**
	 * Sets a field value on a given object
	 *
	 * @param targetObject the object to set the field value on
	 * @param fieldName    exact name of the field
	 * @param fieldValue   value to set on the field
	 * @return true if the value was successfully set, false otherwise
	 */
	public static boolean setField(Object targetObject, Field field, Object fieldValue) {
		if (field == null) {
			return false;
		}
		field.setAccessible(true);
		try {
			field.set(targetObject, fieldValue);
			return true;
		} catch (IllegalAccessException e) {
			return false;
		}
	}
	
	public static List<Field> getAllFields(Object targetObject){
		Set<Field> allFields = new HashSet<>();
		Class<?> superClass = targetObject.getClass();
		while (superClass != null) {
			Field[] fields = targetObject.getClass().getDeclaredFields();
			if (fields != null) {
				for (Field f : fields) {
					allFields.add(f);
				}
			}
			superClass = superClass.getSuperclass();
		}
		return new ArrayList<>(allFields);
	}

}
