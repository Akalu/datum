package org.datum.algorithm;

public enum Algorithm {
	/**
	 * Email generators
	 */
	EMAIL_RANDOM_1,
	EMAIL_LONG(2),
	EMAIL_SHORT(2),
	
	/**
	 * Last name 
	 */
	LAST_NAME_MARKOV_CHAIN_1;

	private int args = 0;
	
	private Algorithm() {
	}
	
	private Algorithm(int args) {
		this.args = args;
	}

	public int getArgs() {
		return args;
	}
	
	public void validate(int args) {
		if (args < this.args) {
			throw new IllegalArgumentException(String.format("Algorithm %s requires %d parameters", this.name(), this.args));
		}
	}

}
