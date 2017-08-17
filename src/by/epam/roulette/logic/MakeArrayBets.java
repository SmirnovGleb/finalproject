package by.epam.roulette.logic;

public class MakeArrayBets {
	public static int[] makeArray(String bets){
		String stringBetArray[] = bets.split(",");
		int[] intbets = new int[stringBetArray.length];
		for(int i = 0; i < intbets.length; i++){
			intbets[i] = Integer.parseInt(stringBetArray[i]);
		}
		return intbets;
	}
}
