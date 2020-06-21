package com.adaptionsoft.games.trivia.the_penalty_box;

import com.adaptionsoft.games.trivia.GameMother;
import com.adaptionsoft.games.trivia.TestsThatAssertViaTheLog;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_in_the_penaltybox extends TestsThatAssertViaTheLog {
    public static final int PLAYER1_1st_ROLL = 1;
    public static final int PLAYER1_2nd_ROLL = 1;
    private Game game;

    @Override
    protected void arrange() {
        game = GameMother
                .createGameWithTwoPlayers()
                .with1stPlayerInThePenaltyBoxAfterRolling(PLAYER1_1st_ROLL)
                .with2ndPlayerAnsweringCorrecly()
                .spawn();
    }

    @Test
    void THEN_you_get_out_by_rolling_odd(){
        game.roll(PLAYER1_2nd_ROLL);
        assertThat(logInterceptor.readLog()).contains("player1 is getting out of the penalty box");
    }

    @Test
    void THEN_you_get_to_move(){
        game.roll(PLAYER1_2nd_ROLL);
        assertThat(logInterceptor.readLog())
                .contains("player1's new location is "+(PLAYER1_1st_ROLL+PLAYER1_2nd_ROLL));
    }

    @Test
    void THEN_you_get_to_answer_a_question(){
        game.roll(PLAYER1_2nd_ROLL);
        assertThat(logInterceptor.readLog())
                .contains("The category is ")
                .contains(" Question 0");
    }

    @Test
    void THEN_you_really_never_actually_get_to_leave_the_penalty_box(){
        game.roll(PLAYER1_2nd_ROLL);
        game.wasCorrectlyAnswered();

        game.roll(1);
        game.wasCorrectlyAnswered();

        game.roll(2);
        assertThat(logInterceptor.readLog()).contains("player1 is not getting out of the penalty box");
    }
}
