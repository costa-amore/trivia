package com.adaptionsoft.games.trivia.ugly.the_penalty_box;

import com.adaptionsoft.games.trivia.ugly.utils.GameMother;
import com.adaptionsoft.games.trivia.ugly.utils.TestsThatAssertViaTheLog;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_in_the_penaltybox_and_rolling_even extends TestsThatAssertViaTheLog {
    public static final int PLAYER1_1st_ROLL = 1;
    private Game game;


    @Override
    protected void arrange() {
        game = GameMother
                .createGameWithTwoPlayers()
                .with1stPlayerInThePenaltyBoxAfterRolling(PLAYER1_1st_ROLL)
                .with2ndPlayerAnsweringCorrecly()
                .spawn();
    }

    private static Stream<Arguments> evenRolls() {

        return Stream.of(Arguments.of(2), Arguments.of(4), Arguments.of(6));

    }

    @ParameterizedTest
    @MethodSource("evenRolls")
    void THEN_you_stay_in(int evenRollResult) {
        game.roll(evenRollResult);
        assertThat(logInterceptor.readLog()).contains("player1 is not getting out of the penalty box");
    }
}
