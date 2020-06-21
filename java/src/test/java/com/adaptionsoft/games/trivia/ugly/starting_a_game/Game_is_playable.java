package com.adaptionsoft.games.trivia.ugly.starting_a_game;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Game_is_playable {

	static Stream<Arguments> validNrOfPlayers(){
		return Stream.of(
				Arguments.of(2)
				,Arguments.of(3)
				,Arguments.of(4)
				,Arguments.of(5)
		);
	}

	static Stream<Arguments> tooManyPlayers(){
		return Stream.of(
				Arguments.of(6)
				,Arguments.of(10)
		);
	}

	@ParameterizedTest
	@MethodSource("validNrOfPlayers")
	public void When_adding_valid_nr_of_players(int nrOfPlayersAdded) {
		assertTrue(aGameWith(nrOfPlayersAdded).isPlayable());
	}

	@ParameterizedTest
	@MethodSource("tooManyPlayers")
	public void When_adding_too_many_players(int nrOfPlayersAdded) {
		assertTrue(aGameWith(nrOfPlayersAdded).isPlayable());
	}

	@ParameterizedTest
	@MethodSource("tooManyPlayers")
	public void When_adding_too_many_players_they_are_ignored(int nrOfPlayersAdded) {
		assertTrue(aGameWith(nrOfPlayersAdded).howManyPlayers() < nrOfPlayersAdded);
	}

	private Game aGameWith(int nrOfPlayers) {
		Game aGame = new Game();

		for (int i = 0; i < nrOfPlayers; i++) {
			aGame.add(Integer.toString(i+1));
		}
		return aGame;
	}
}

