package org.datum.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * Utility class covering reflection operations of POJOs
 * 
 * @author akaliutau
 *
 */
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
		if (field.getType().isPrimitive() && fieldValue == null) {// do not set primitive values to null
			return true;
		}
		try {
			field.set(targetObject, fieldValue);
			return true;
		} catch (IllegalAccessException e) {
			return false;
		}
	}
	
	/**
	 * Scan and return all non-static, non-transient fields
	 * @param targetObject
	 * @return
	 */
	public static List<Field> getAllFields(@Nullable Object targetObject){
		Set<Field> allFields = new HashSet<>();
		Class<?> superClass = targetObject == null ? null : targetObject.getClass();
		while (superClass != null) {
			Field[] fields = targetObject.getClass().getDeclaredFields();
			if (fields != null) {
				for (Field f : fields) {
					if (!haveModifiers(f.getModifiers(), Modifier.STATIC | Modifier.TRANSIENT)){
						allFields.add(f);
					}
				}
			}
			superClass = superClass.getSuperclass();
		}
		return new ArrayList<>(allFields);
	}
	
	public static boolean haveModifiers(int mod, int mask) {
		return (mod & mask) != 0;
	}
	
	public static boolean isStatic(int mod) {
		return (mod & Modifier.STATIC) != 0;
	}
	
	public static boolean isTransient(int mod) {
		return (mod & Modifier.TRANSIENT) != 0;
	}

}
