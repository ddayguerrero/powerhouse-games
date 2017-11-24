package org.soen387.util;

/**
 * Carries out the validation for credit cards
 * 
 */
public class LuhnValidator {

	public static boolean isValid(String value) {
		return luhnCheck(value.replaceAll("\\D", "")); // remove non-digits
	}

	/**
	 * Luhn Check validation algorithm
	 * 
	 * @param cardNumber
	 * @return is a valid credit card number
	 */
	private static boolean luhnCheck(String cardNumber) {
		int sum = 0;

		for (int i = cardNumber.length() - 1; i >= 0; i -= 2) {
			sum += Integer.parseInt(cardNumber.substring(i, i + 1));
			if (i > 0) {
				int d = 2 * Integer.parseInt(cardNumber.substring(i - 1, i));
				if (d > 9)
					d -= 9;
				sum += d;
			}
		}

		return sum % 10 == 0;
	}
}
