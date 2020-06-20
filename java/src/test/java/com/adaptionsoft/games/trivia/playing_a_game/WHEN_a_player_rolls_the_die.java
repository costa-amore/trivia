package com.adaptionsoft.games.trivia.playing_a_game;

import com.adaptionsoft.games.trivia.GameMother;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_a_player_rolls_the_die extends TestsThatAssertViaTheLog {

    @Test
    void THEN_they_move_that_amount_on_the_board() {
        Game game = GameMother
                .createGameWithTwoPlayers()
                .spawn();

        ByteArrayOutputStream log = captureTheLogging();

        game.roll(1);

        assertThat(log.toString()).contains("player1's new location is 1");

    }

}
