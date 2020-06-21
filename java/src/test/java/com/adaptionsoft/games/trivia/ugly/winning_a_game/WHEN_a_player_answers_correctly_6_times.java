package com.adaptionsoft.games.trivia.ugly.winning_a_game;

import com.adaptionsoft.games.trivia.ugly.utils.GameMother;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_a_player_answers_correctly_6_times {

    @Test
    void THEN_that_player_wins(){
        Game game = GameMother
                .createGameWithTwoPlayers()
                .withPerfectRoundsAlreadyPlayed(5)
                .spawn();

        game.roll(1);
        assertThat(game.wasCorrectlyAnswered()).isFalse();

    }

}
