package by.epam.roulette.entity;

import java.util.Random;

public class Wheel {
	public static int rotateWheel(){
		return new Random().nextInt(37);
	}
}
