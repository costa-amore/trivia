package com.adaptionsoft.games.trivia.playing_a_game;

import com.adaptionsoft.games.trivia.GameMother;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_a_player_rolls_the_die extends TestsThatAssertViaTheLog {

    private Game game;
    private ByteArrayOutputStream log;

    static Stream<Arguments> examples(){
        return Stream.of(
                Arguments.of(1, 1, "Science")
                ,Arguments.of(2, 2, "Sports")
                ,Arguments.of(3, 3, "Rock")
                ,Arguments.of(4, 4, "Pop")
                ,Arguments.of(5, 5, "Science")
                ,Arguments.of(6, 6, "Sports")
        );
    }

    @Override
    void arrange() {

        game = GameMother
                .createGameWithTwoPlayers()
                .spawn();
        log = captureTheLogging();

    }

    @ParameterizedTest
    @MethodSource("examples")
    void THEN_they_move_that_amount_on_the_board(int rollResult, int expectedLocation, String expectedQuestionCategory) {

        game.roll(rollResult);

        assertThat(log.toString()).contains("player1's new location is "+ expectedLocation);
    }

    @ParameterizedTest
    @MethodSource("examples")
    void THEN_they_get_a_question_from_the_expected_category(int rollResult, int expectedLocation, String expectedQuestionCategory) {

        game.roll(rollResult);

        assertThat(log.toString()).contains("The category is "+ expectedQuestionCategory);
    }
}
