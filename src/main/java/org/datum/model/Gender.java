package org.datum.model;

/**
 * Gender structure
 * @author akaliutau
 *
 */
public enum Gender {
	FEMALE(0.48), 
	MALE(0.46), 
	NON_BINARY(0.06);
	
	private double prob; // probability of some random person to have specific gender

	private Gender(double prob) {
		this.prob = prob;
	}
	
	public double getProbability() {
		return prob;
	}
}
