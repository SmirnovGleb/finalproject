package by.epam.roulette.logic;

import java.math.BigDecimal;

import by.epam.roulette.entity.BetsRate;

/**
 * The Class CalculateWinnings.
 */
public class CalculateWinnings {
	
	/**
	 * Calculate.
	 *
	 * @param money
	 * @param countBetNumbers
	 * @return the big decimal
	 */
	public static BigDecimal calculate(BigDecimal money, int countBetNumbers) {
		BetsRate betsRate = null;
		switch (countBetNumbers) {
		case 1:
			betsRate = BetsRate.STRAIGHTUP;
			break;
		case 2:
			betsRate = BetsRate.SPLIT;
			break;
		case 3:
			betsRate = BetsRate.STREET;
			break;
		case 4:
			betsRate = BetsRate.CORNER;
			break;
		case 6:
			betsRate = BetsRate.LINE;
			break;
		case 12:
			betsRate = BetsRate.ONETHIRD;
			break;
		case 18:
			betsRate = BetsRate.HALFBET;
			break;
		}
		return money.multiply(betsRate.getPayoff());
	}
}
