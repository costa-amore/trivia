package com.adaptionsoft.games.trivia.GameSetup;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class Game_is_NOT_playable {
	static Stream<Arguments> notEnoughPlayers(){
		return Stream.of(
 				 Arguments.of(0)
				,Arguments.of(1)
		);
	}

	@ParameterizedTest
	@MethodSource("notEnoughPlayers")
	public void When_there_are_not_enough_players(int nrOfPlayers) {
		assertFalse(aGameWith(nrOfPlayers).isPlayable());
	}

	private Game aGameWith(int nrOfPlayers) {
		Game aGame = new Game();

		for (int i = 0; i < nrOfPlayers; i++) {
			aGame.add(Integer.toString(i + 1));
		}
		return aGame;
	}
}

