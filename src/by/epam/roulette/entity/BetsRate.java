package by.epam.roulette.entity;

import java.math.BigDecimal;

/**
 * The Enum BetsRate.
 */
public enum BetsRate {
	STRAIGHTUP(35, 1), SPLIT(17, 2), STREET(11, 3), CORNER(8, 4), LINE(5, 6), ONETHIRD(2, 12), HALFBET(1, 18);

	private int payoff;
	private int countNumbers;

	/**
	 * Instantiates a new bets rate.
	 *
	 * @param payoff
	 * @param countNumbers
	 */
	private BetsRate(int payoff, int countNumbers) {
		this.payoff = payoff;
		this.countNumbers = countNumbers;
	}

	/**
	 * Gets the payoff.
	 *
	 * @return the payoff
	 */
	public BigDecimal getPayoff() {
		return new BigDecimal(payoff);
	}

	/**
	 * Gets the count numbers.
	 *
	 * @return the count numbers
	 */
	public int getCountNumbers() {
		return countNumbers;
	}
}
