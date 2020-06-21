package com.adaptionsoft.games.trivia.ugly.playing_a_game;

import com.adaptionsoft.games.trivia.ugly.utils.GameMother;
import com.adaptionsoft.games.trivia.ugly.utils.TestsThatAssertViaTheLog;
import com.adaptionsoft.games.uglytrivia.Game;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class WHEN_a_player_reaches_the_end_of_the_board extends TestsThatAssertViaTheLog {

    private Game game;

    private final int ROUNDS_PLAYED = 2;
    private final int ROLLS_EACH_ROUND = 5;

    static Stream<Arguments> examples(){
        return Stream.of(
                Arguments.of(1, 11)
                ,Arguments.of(2, 0)
                ,Arguments.of(3, 1)
                ,Arguments.of(4, 2)
                ,Arguments.of(5, 3)
        );
    }

    @Override
    protected void arrange() {
        game = GameMother
                .createGameWithTwoPlayers()
                .WithEveryRoundEachPlayerRolling(ROUNDS_PLAYED, ROLLS_EACH_ROUND)
                .spawn();
    }

    @ParameterizedTest @MethodSource("examples")
    void THEN_they_turn_around_to_the_starting_position(int rolledResult, int expectedLocation){

        game.roll(rolledResult);

        Assertions.assertThat(
                logInterceptor.readLog()).as("from current position: "+ ROUNDS_PLAYED * ROLLS_EACH_ROUND)
                .contains("player1's new location is "+ expectedLocation);
    }
}
