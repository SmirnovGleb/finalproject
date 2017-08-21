package by.epam.roulette.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserParametersValidator {
	private static final String LOGIN_PASSWORD_REGEX = "\\w{3,15}";
	private static final String EMAIL_REGEX = "^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\\.)+[a-z]{2,6}$";

	public static boolean validateLoginPassword(String password) {
		Pattern pattern = Pattern.compile(LOGIN_PASSWORD_REGEX);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public static boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
