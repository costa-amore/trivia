package com.adaptionsoft.games.trivia.nice.starting_a_game;

import com.adaptionsoft.games.nicetrivia.game.Game;
import com.adaptionsoft.games.nicetrivia.game.StartableGame;
import com.adaptionsoft.games.nicetrivia.player.Player;
import com.adaptionsoft.games.nicetrivia.question.Jury;
import com.adaptionsoft.games.trivia.nice.QuestionsMother;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_a_game_has_not_enough_questions {
    private final QuestionsMother questionsMother = new QuestionsMother();

    private static Stream<Arguments> examples() {
        return Stream.of(
                Arguments.of(0)
                ,Arguments.of(1)
        );
    }

    @ParameterizedTest
    @MethodSource("examples")
    void THEN_the_game_can_NOT_start(int nrOfQuestions){

        Game game = Game
                .createNewGame()
                .add(Jury.createAround(questionsMother.create(nrOfQuestions)))
                .add(Player.create("1"))
                .add(Player.create("2"))
                ;


        assertThat(game).isNotExactlyInstanceOf(StartableGame.class);
    }

}
