package com.adaptionsoft.games.trivia.ugly.playing_a_game;

import com.adaptionsoft.games.trivia.ugly.utils.GameMother;
import com.adaptionsoft.games.trivia.ugly.utils.TestsThatAssertViaTheLog;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_a_player_rolls_the_die extends TestsThatAssertViaTheLog {

    private Game game;

    static Stream<Arguments> locationExamples(){
        return Stream.of(
                Arguments.of(1, 1)
                ,Arguments.of(2, 2)
                ,Arguments.of(3, 3)
                ,Arguments.of(4, 4)
                ,Arguments.of(5, 5)
                ,Arguments.of(6, 6)
        );
    }
    static Stream<Arguments> questionCategoryExamples(){
        return Stream.of(
                Arguments.of(1, "Science")
                ,Arguments.of(2, "Sports")
                ,Arguments.of(3, "Rock")
                ,Arguments.of(4, "Pop")
                ,Arguments.of(5, "Science")
                ,Arguments.of(6, "Sports")
        );
    }

    @Override
    protected void arrange() {

        game = GameMother
                .createGameWithTwoPlayers()
                .spawn();

    }

    @ParameterizedTest @MethodSource("locationExamples")
    void THEN_you_move_that_amount_on_the_board(int rollResult, int expectedLocation) {

        game.roll(rollResult);

        assertThat(logInterceptor.readLog()).contains("player1's new location is "+ expectedLocation);
    }

    @ParameterizedTest @MethodSource("questionCategoryExamples")
    void THEN_you_get_a_question_from_the_expected_category(int rollResult, String expectedQuestionCategory) {

        game.roll(rollResult);

        assertThat(logInterceptor.readLog()).contains("The category is "+ expectedQuestionCategory);
    }
}
