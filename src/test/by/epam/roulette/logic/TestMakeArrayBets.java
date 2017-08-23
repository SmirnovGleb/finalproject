package test.by.epam.roulette.logic;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.roulette.logic.MakeArrayBets;

public class TestMakeArrayBets {
	private static final String INPUT_STRING = "1,2,4,5";
	private static final String BAD_INPUT_STRING = "bad String";
	private static int[] expectedArray;

	@BeforeClass
	public static void initParameters() {
		expectedArray = new int[] { 1, 2, 4, 5 };
	}

	@Test
	public void testMakeArray() {
		MakeArrayBets.makeArray(BAD_INPUT_STRING);
		Assert.assertTrue(Arrays.equals(MakeArrayBets.makeArray(INPUT_STRING), expectedArray));
	}

	@Test(expected = NumberFormatException.class)
	public void testMakeArrayException() throws RuntimeException {
		MakeArrayBets.makeArray(BAD_INPUT_STRING);
	}

	@AfterClass
	public static void destroyData() {
		expectedArray = null;
	}

}
