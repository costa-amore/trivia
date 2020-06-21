package com.adaptionsoft.games.trivia.nice.starting_a_game;

import com.adaptionsoft.games.nicetrivia.game.Game;
import com.adaptionsoft.games.nicetrivia.game.GameExporter;
import com.adaptionsoft.games.nicetrivia.player.Player;
import com.adaptionsoft.games.nicetrivia.player.PlayerInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WHEN_a_player_joins_the_game {
    private PlayerInformation playerInformation;

    @BeforeEach
    private void Given() {
        Game game = Game.createNewGame()
                .addPlayer(Player.create("player 1"));

        playerInformation = GameExporter.export(game).findPlayer("player 1");
    }

    @Test
    void THEN_they_are_at_position_zero(){
        assertThat(playerInformation.playerPosition).isEqualTo("0");
    }

    @Test
    void THEN_they_are_not_in_the_penalty_box(){
        assertThat(playerInformation.status).isEqualTo("ready");
    }

    @Test
    void THEN_they_have_zero_gold(){
        assertThat(playerInformation.playerGold).isEqualTo("0");
    }
}
