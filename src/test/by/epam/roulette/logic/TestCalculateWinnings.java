package test.by.epam.roulette.logic;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import by.epam.roulette.logic.CalculateWinnings;

public class TestCalculateWinnings {
	private static final BigDecimal EXPECTED_WINNING = new BigDecimal("10");

	@Test
	public void testCalculate() {
		BigDecimal testWinning = CalculateWinnings.calculate(new BigDecimal("10"), 18);
		Assert.assertTrue(testWinning.equals(EXPECTED_WINNING));
	}

	@Test
	public void testCalculateWithBadData() {
		Assert.assertNotEquals(EXPECTED_WINNING, CalculateWinnings.calculate(new BigDecimal("10"), 1));
	}
}
