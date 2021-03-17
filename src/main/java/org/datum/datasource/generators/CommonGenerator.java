package org.datum.datasource.generators;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class CommonGenerator {

	static Random random = new Random();

	final static char[] vowels = { 'a', 'o', 'u', 'i', 'e' };
	final static char[] consonants = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't',
			'v', 'w', 'x', 'y', 'z' };

	public static Set<Character> toSet(char[] chars) {
		Set<Character> set = new HashSet<>();
		for (char c : chars) {
			set.add(c);
		}
		return set;
	}

	/**
	 * Returns a random value picked up from input array of elements
	 */
	public static <T> T getAny(T[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		return arr[random.nextInt(arr.length)];
	}

	/**
	 * Returns a random value picked up from input array of elements based on
	 * probability array probability SUM<probabilities> must be <= 100
	 * 
	 * val1 val2 val3 
	 * 0.10 0.60 0.30
	 * 
	 * Algorithm: 
	 * 
	 * 1) generate random number in the range [0,1) 
	 * 
	 * 2) use map to choose the value (index of element) on the basis of input probability f.e. 0.45 =>
	 * 
	 */
	public static <T> T getAnyDistributed(T[] arr, double[] probability) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		double sum = Arrays.stream(probability).sum();
		if (sum > 1.0d) {
			throw new IllegalArgumentException("The sum of all probabilities must be 1.0 at most");
		}
		double num = random.nextDouble();
		double runningSum = 0.0d;

		for (int i = 0; i < arr.length; i++) {
			runningSum += probability[i] / sum;
			if (num <= runningSum) {
				return arr[i];
			}
		}
		// should never go here
		return null;
	}

	public static char getAny(char[] arr) {
		return arr[random.nextInt(arr.length)];
	}

	public static int getAny(int[] arr) {
		return arr[random.nextInt(arr.length)];
	}

	public static int getAnyInRange(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public static LocalDate getRandomDate(int rangeFromNow) {
		LocalDate startDate = LocalDate.now(); // start date
		if (rangeFromNow <= 0) {
			return startDate;
		}
		long start = startDate.toEpochDay();

		LocalDate endDate = startDate.plusDays(random.nextInt(rangeFromNow) + 1); // end date
		long end = endDate.toEpochDay();

		long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
		return LocalDate.ofEpochDay(randomEpochDay); // random date between the range
	}

}
