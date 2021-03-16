package org.datum.datasource.generators;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CommonGenerator {
	
	static Random random = new Random();

	
	final static char[] vowels = { 'a', 'o', 'u', 'i', 'e' };
	final static char[] consonants = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r',
			's', 't', 'v', 'w', 'x', 'y', 'z' };

	public static Set<Character> toSet(char[] chars) {
		Set<Character> set = new HashSet<>();
		for (char c : chars) {
			set.add(c);
		}
		return set;
	}

	public static <T> T getAny(T[] arr) {
		return arr[random.nextInt(arr.length)];
	}

	public static char getAny(char[] arr) {
		return arr[random.nextInt(arr.length)];
	}

	public static int getAny(int[] arr) {
		return arr[random.nextInt(arr.length)];
	}


}
