package com.adaptionsoft.games.trivia.ugly.the_penalty_box;

import com.adaptionsoft.games.trivia.ugly.utils.GameMother;
import com.adaptionsoft.games.trivia.ugly.utils.TestsThatAssertViaTheLog;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_answering_wrongly extends TestsThatAssertViaTheLog {
    private Game game;

    @Override
    protected void arrange() {
        game = GameMother
                .createGameWithTwoPlayers()
                .spawn();
        game.roll(1);
    }

    @Override
    protected void act() {
        game.wrongAnswer();
        game.roll(1);
    }

    @Test
    void THEN_you_go_to_the_penalty_box(){
        assertThat(logInterceptor.readLog()).contains("player1 was sent to the penalty box");
    }

    @Test
    void THEN_you_cant_answer_a_question(){
        assertThat(logInterceptor.readLog()).contains(
                "Question was incorrectly answered\r\n" +
                "player1 was sent to the penalty box\r\n" +
                "player2 is the current player");
    }

    @Test
    void THEN_you_get_no_gold(){
        assertThat(logInterceptor.readLog()).doesNotContain("player1 now has 1 Gold Coins.");
    }
}
