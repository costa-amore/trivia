package com.adaptionsoft.games.trivia.nice.starting_a_game;

import com.adaptionsoft.games.nicetrivia.game.Game;
import com.adaptionsoft.games.nicetrivia.player.Player;
import com.adaptionsoft.games.nicetrivia.game.StartableGame;
import com.adaptionsoft.games.nicetrivia.question.Jury;
import com.adaptionsoft.games.nicetrivia.question.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_a_game_has_less_than_2_players {
    @Test
    void THEN_the_game_can_NOT_start(){
        List<Question> questions = new ArrayList<>(Arrays.asList(Question.create("Q1"), Question.create("Q2")));
        Game game = Game.createNewGame()
                .add(Jury.createAround(questions))
                .add(Player.create("1"));

        assertThat(game).isNotExactlyInstanceOf(StartableGame.class);
    }
}
