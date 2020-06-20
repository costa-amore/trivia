
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		Game aGame = new Game();
		
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		Random rand = InitializeRandomizerWith(args);
		Die d6 = new Die(rand, 6);
	
		do {

			rollTheDice(aGame, d6);
			answerTheQuestion(aGame, rand);

		} while (notAWinner);
		
	}

	private static Random InitializeRandomizerWith(String[] args) {
		if (args == null) return new Random();
		if (args.length<1) return  new Random();
		if (tryParseInt(args[0])) {
			final int seed = Integer.parseInt(args[0]);
			return new Random(seed);
		}
		return new Random();
	}

	private static boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static void answerTheQuestion(Game aGame, Random rand) {
		if (oneChanceOutOfNine(rand)) {
			notAWinner = aGame.wrongAnswer();
		} else {
			notAWinner = aGame.wasCorrectlyAnswered();
		}
	}

	private static void rollTheDice(Game aGame, Die d6) {
		aGame.currentPlayerRolled(d6.roll());
	}

	private static boolean oneChanceOutOfNine(Random rand) {
		final int randomNumber = rand.nextInt(9);
		return randomNumber == 7;
	}

}

class Die
{
	private final int sides;
	private final Random roll;

	public Die(Random randomizer, int sides){
		roll = randomizer;
		this.sides = sides;
	}

	int roll(){
		final int nextRandomNumber = roll.nextInt(sides - 1);
		return nextRandomNumber +1;
	}
}
