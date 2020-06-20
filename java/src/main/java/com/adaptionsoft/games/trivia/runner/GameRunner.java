
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
		
		Random rand = new Random();
		Die d6 = new Die(6);
	
		do {
			
			aGame.currentPlayerRolled(d6.roll());
			
			if (oneChanceOutOfNine(rand)) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
			
		} while (notAWinner);
		
	}

	private static boolean oneChanceOutOfNine(Random rand) {
		return rand.nextInt(9) == 7;
	}

}

class Die
{
	private final int sides;
	private final Random roll;

	public Die(int sides){
		roll = new Random();
		this.sides = sides;
	}

	int roll(){
		return roll.nextInt(sides-1)+1;
	}
}
