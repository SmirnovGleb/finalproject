package test.by.epam.roulette.converter;

import org.junit.Assert;
import org.junit.Test;

import by.epam.roulette.converter.MD5Converter;

/**
 * The Class TestMD5Converter.
 */
public class TestMD5Converter {
	private static final String INPUT_STRING = "test";
	private static final String BAD_STRING = "bad string";
	private static final String EXPECTED_STRING = "098f6bcd4621d373cade4e832627b4f6";
	
	/**
	 * Convertation's test.
	 */
	@Test
	public void testConvert() {
		Assert.assertTrue(new MD5Converter().convert(INPUT_STRING).equals(EXPECTED_STRING));
	}
	
	/**
	 * Convertation's test with bad data.
	 */
	@Test
	public void testConvertBadData() {
		Assert.assertFalse(new MD5Converter().convert(BAD_STRING).equals(EXPECTED_STRING));
	}
}
