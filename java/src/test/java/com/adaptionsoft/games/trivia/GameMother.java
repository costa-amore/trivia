package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;

import java.util.ArrayList;
import java.util.List;

class GameBuilder {
    private List<String> players;

    GameBuilder() {
        players = new ArrayList<>();
    }

    Game build() {
        Game game = new Game();
        for (String player : players) {
            game.add(player);
        }
        return game;
    }

    public GameBuilder WithPlayer(String player) {
        players.add(player);
        return this;
    }

}


public class GameMother {

    //Factory
    public static GameMother createGameWithTwoPlayers() {
        return new GameMother(new GameBuilder()
                .WithPlayer("player1")
                .WithPlayer("player2"));
    }

    private final Game game;

    public Game spawn() {
        return game;
    }

    // default children
    private GameMother(GameBuilder gameBuilder) {
        game = gameBuilder.build();
    }

    // variations
    public GameMother withPerfectRoundsAlreadyPlayed(int nrOfRoundsPlayed) {
        return WithEveryPerfectRoundEachPlayerRolling(nrOfRoundsPlayed,1);
    }

    public GameMother WithEveryRoundEachPlayerRolling(int nrOfRoundsPlayed, int rollResultsEachRound) {
        return WithEveryPerfectRoundEachPlayerRolling(nrOfRoundsPlayed, rollResultsEachRound);
    }

    public GameMother WithEveryPerfectRoundEachPlayerRolling(int nrOfRoundsPlayed, int rollResultsEachRound) {
        for (int round = 0; round < nrOfRoundsPlayed; round++) {
            for (int player = 0; player < game.howManyPlayers(); player++) {
                game.roll(rollResultsEachRound);
                game.wasCorrectlyAnswered();
            }
        }
        return this;
    }

    public GameMother with2ndPlayerAnsweringCorrecly() {
        game.roll(1);
        game.wasCorrectlyAnswered();
        return this;
    }

    public GameMother with1stPlayerInThePenaltyBoxAfterRolling(int rollResult) {
        game.roll(rollResult);
        game.wrongAnswer();
        return this;
    }
}