package com.adaptionsoft.games.trivia.nice;

import com.adaptionsoft.games.nicetrivia.game.Game;
import com.adaptionsoft.games.nicetrivia.player.Player;
import com.adaptionsoft.games.nicetrivia.game.StartableGame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_a_game_has_less_than_2_players {
    @Test
    void THEN_the_game_can_start(){
        Game game = Game.createNewGame()
                .addPlayer(Player.create("1"));

        assertThat(game).isNotExactlyInstanceOf(StartableGame.class);
    }
}
