package com.adaptionsoft.games.trivia.playing_a_game;

import com.adaptionsoft.games.trivia.GameMother;
import com.adaptionsoft.games.trivia.SystemOutInterceptor;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_answering_a_question_correctly {

    private static SystemOutInterceptor logInterceptor = new SystemOutInterceptor();

    static Stream<Arguments> examples() {
        return Stream.of(
                Arguments.of(1, 1)
                , Arguments.of(2, 2)
                , Arguments.of(3, 3)
                , Arguments.of(4, 4)
                , Arguments.of(5, 5)
                , Arguments.of(6, 6)
        );
    }

    @ParameterizedTest @MethodSource("examples")
    void THEN_they_get_gold(int correctAnswers, int expectedGold) {

        Game game = GameMother
                .createGameWithTwoPlayers()
                .withPerfectRoundsAlreadyPlayed(correctAnswers - 1)//the last answer must be intercepted
                .spawn();
        game.roll(1);

        logInterceptor.startIntercepting();
        game.wasCorrectlyAnswered();

        assertThat(logInterceptor.readLog()).contains("player1 now has " + expectedGold + " Gold Coins.");
    }

    @AfterAll
    static void releaseLogging(){
        logInterceptor.returnToNormal();
    }

}
