package org.datum.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IBANRecord {
	private int length;
	private String country;
	private String prefix;
	private int checkSum = 0;
	private String format;
	private List<CharsGroup> groups = new ArrayList<>();
	
	public IBANRecord(String country, int length, String prefix, String format, CharsGroup... groups) {
		this.length = length;
		this.country = country;
		this.prefix = prefix;
		this.groups = Arrays.asList(groups);
		this.format = format;
	}
	
	public IBANRecord(String country, int length, String prefix, String format, int checkSum, CharsGroup... groups) {
		this.length = length;
		this.country = country;
		this.prefix = prefix;
		this.groups = Arrays.asList(groups);
		this.format = format;
		this.checkSum = checkSum;
	}

	public IBANRecord clone() {
		CharsGroup[] groups = new CharsGroup[this.groups.size()];
		int idx = 0;
		for (CharsGroup group : this.groups) {
			groups[idx ++] = group.clone();
		}
		return new IBANRecord(this.country, this.length, this.prefix, this.format, this.checkSum, groups);
	}
	
	public boolean hasDefaultChecksum() {
		return checkSum != 0;
	}
	
	public String getUnformattedNumber() {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		if (checkSum < 10) {
			sb.append('0');
			sb.append((char) ('0' + checkSum));
		}else {
			sb.append((char) ('0' + checkSum / 10));
			sb.append((char) ('0' + checkSum % 10));
		}
		for (CharsGroup group : groups) {
			sb.append(group.getStringValue());
		}

		return sb.toString();
	}
	
	public String getFormattedNumber() {
		String str = getUnformattedNumber();
		StringBuilder sb = new StringBuilder();
		int counter = 4;
		for (char c : str.toCharArray()) {
			if (counter == 0) {
				counter = 4;
				sb.append(' ');
			}
			sb.append(c);
			counter --;
		}
		return sb.toString();
	}
	
	

}
