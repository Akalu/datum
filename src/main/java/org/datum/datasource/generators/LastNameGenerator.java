package org.datum.datasource.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static org.datum.datasource.generators.CommonGenerator.*;

public class LastNameGenerator {

	public static enum Algorithm {
		MARKOV_CHAIN_ALG_1
	}

	private final static String[] prefixDigraphs = new String[] { "en", "re", "er", "th", "on", "in", "an", "or", "st",
			"ve", "es", "to", "se", "at", "ti", "ar", "as", "co", "io", "ty", "fo", "fi", "ra", "et", "le", "ou", "ma",
			"tw", "ea", "is", "si", "de", "hi", "al", "ce", "da", "ec", "ur", "ri", "el", "la", "ro", "ta" };

	private final static String[] suffixDigraphs = new String[] { "rt", "te", "ed", "ne", "nd", "ni", "nt", "rs" };

	private final static String[] majorPrefix = new String[] { "De", "Der", "El", "Fitz", "Mac", "Mc", "O'", "Van" };

	private final static String[] minorPrefix = new String[] { "Kil", "West" };

	private final static String[] suffix = new String[] { "all", "ard", "beck", "berg", "bert", "born", "boro", "brick",
			"bridge", "brier", "brook", "buck", "by", "cock", "dale", "drow", "ell", "ett", "field", "ford", "forth",
			"gate", "ham", "hard", "hart", "hill", "horn", "house", "ings", "kins", "lake", "land", "ley", "lin", "low",
			"maker", "man", "mond", "more", "miller", "ner", "nett", "ott", "quist", "rich", "rick", "ridge", "shaw",
			"shire", "smith", "stone", "strom", "thal", "ton", "tree", "wald", "ward", "well", "wick", "win", "wood",
			"worth", "son", "man" };

	// used to filter our rouge names
	private final static Pattern multipleConsonantsRegex = Pattern
			.compile("[b-df-hj-np-tv-z]{2}");
	private final static Pattern multipleVowelsRegex = Pattern.compile("([aeiou]{2})");

	private final static char[] invalidPairs = { 'd', 'g', 'h', 'j', 'k', 'm', 'p', 'r', 's', 'v', 'w', 'x', 'z' };

	private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";

	private final static char[] abc = alphabet.toCharArray();
	private final static char[] abcCapital = alphabet.toUpperCase().toCharArray();

	private final static Set<Character> vowelsSet = toSet(vowels);
	private final static Set<Character> consonantsSet = toSet(consonants);
	private final static Set<Character> invalidPairsSet = toSet(invalidPairs);

	/**
	 * Factory of available algorithms
	 * 
	 * @param algorithm
	 * @return
	 */

	private final static Map<Algorithm, Supplier<String>> algs = new HashMap<>();

	static {
		algs.put(Algorithm.MARKOV_CHAIN_ALG_1, () -> generateNameMarkovChainAlgorithm1());
	}

	public static String generateName(Algorithm algorithm) {
		return algs.get(algorithm).get();
	}

	/**
	 * Available algorithms
	 * 
	 * @param algorithm
	 * @return
	 */

	private static String generateNameMarkovChainAlgorithm1() {

		StringBuilder potentialName = new StringBuilder();
		List<Integer> toCapitilize = new ArrayList<>();
		if (random.nextInt(10) < 2) {
			potentialName.append(getAny(majorPrefix));
			toCapitilize.add(potentialName.length());
		}else if (random.nextInt(10) < 2) {
			potentialName.append(getAny(minorPrefix));
		} else {
			toCapitilize.add(0);
		}
		String middle = null;
		if (random.nextBoolean()) {// use prefixDigraphs digraph and add 1 letter after
			middle = getAny(prefixDigraphs);
			potentialName.append(middle);
			if (vowelsSet.contains(middle.charAt(1))) {
				potentialName.append(getAny(consonants));
			} else {
				potentialName.append(getAny(vowels));
			}
		} else {// use suffixDigraphs digraph and add 1 letter before
			middle = getAny(suffixDigraphs);
			if (vowelsSet.contains(middle.charAt(0))) {
				potentialName.append(getAny(consonants));
			} else {
				potentialName.append(getAny(vowels));
			}
			potentialName.append(middle);
		}
		potentialName.append(getAny(suffix));
		clean(potentialName);

		setFirstToCapital(potentialName, toCapitilize);

		return potentialName.toString();
	}

	public static void setFirstToCapital(StringBuilder s, List<Integer> toCapitilize) {
		for (Integer idx : toCapitilize) {
			if (idx < s.length()) {
				char have = s.charAt(idx);
				char newChar = abcCapital[have - 'a'];
				s.setCharAt(idx, newChar);
			}
		}
	}

	public static void clean(StringBuilder s) {
		int last = 0;
		int n = s.length();
		if (n == 0) {
			return;
		}
		char unique = s.charAt(0);// unique
		int counter = 1;
		for (int i = 1; i < n; i++) {
			char cur = s.charAt(i);
			if (unique == cur && invalidPairsSet.contains(unique)) {
				counter++;
			} else {
				counter = 1;
			}
			if (counter == 1) {// collect only unique numbers
				s.setCharAt(last, unique);
				last++;
			}
			unique = cur;
		}
		s.setCharAt(last, unique);
		s.setLength(last + 1);
	}
	
	public static boolean containsDoubleConsonants(String s) {
		if (s == null) {
			return false;
		}
		return multipleConsonantsRegex.matcher(s.trim()).find();
	}
	
	public static boolean containsDoubleVowels(String s) {
		if (s == null) {
			return false;
		}
		return multipleVowelsRegex.matcher(s.trim()).find();
	}

}
