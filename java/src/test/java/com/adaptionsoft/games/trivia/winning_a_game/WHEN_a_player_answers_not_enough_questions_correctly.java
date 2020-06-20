package com.adaptionsoft.games.trivia.winning_a_game;

import com.adaptionsoft.games.trivia.GameMother;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_a_player_answers_not_enough_questions_correctly {
    static Stream<Arguments> examples() {
        return Stream.of(
                Arguments.of(1)
                , Arguments.of(2)
                , Arguments.of(3)
                , Arguments.of(4)
                , Arguments.of(5)
        );
    }


    @ParameterizedTest
    @MethodSource("examples")
    void THEN_that_player_doesnt_win_yet(int nrOfPerfectRounds) {
        Game game = GameMother
                .createGameWithTwoPlayers()
                .withPerfectRoundsAlreadyPlayed(nrOfPerfectRounds - 1)
                .spawn();

        game.roll(1);
        assertThat(game.wasCorrectlyAnswered()).isTrue();

    }
}
