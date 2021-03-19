package org.datum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class CharsGroup {
	
	private final char[] chars;
	private final CharType type;
	
	public static CharsGroup of(int len, CharType type) {
			return new CharsGroup(new char[len], type);
	}
	
	public CharsGroup clone() {
		return new CharsGroup(chars, type); 
	}
	
	public String getStringValue() {
		return new String(chars);
	}

}
