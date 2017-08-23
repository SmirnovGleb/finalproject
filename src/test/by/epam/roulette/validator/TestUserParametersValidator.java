package test.by.epam.roulette.validator;

import org.junit.Assert;
import org.junit.Test;

import by.epam.roulette.validator.UserParametersValidator;

public class TestUserParametersValidator {
	private static final String GOOD_PASSWORD = "password";
	private static final String BAD_PASSWORD = "ce";
	private static final String GOOD_EMAIL = "goodemail@email.com";
	private static final String BAD_EMAIL = "bademail";

	@Test
	public void testValidateLoginPassword() {
		Assert.assertTrue(UserParametersValidator.validateLoginPassword(GOOD_PASSWORD));
	}

	@Test
	public void testValidateEmail() {
		Assert.assertTrue(UserParametersValidator.validateEmail(GOOD_EMAIL));
	}

	@Test
	public void testBadValidateLoginPassword() {
		Assert.assertFalse(UserParametersValidator.validateLoginPassword(BAD_PASSWORD));
	}

	@Test
	public void testBadValidateEmail() {
		Assert.assertFalse(UserParametersValidator.validateEmail(BAD_EMAIL));
	}
}
