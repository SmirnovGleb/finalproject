package by.epam.roulette.entity;

import java.util.Random;

/**
 * The Class Wheel.
 */
public class Wheel {
	
	/**
	 * Rotate wheel.
	 *
	 * @return the int
	 */
	public static int rotateWheel() {
		return new Random().nextInt(37);
	}
}
