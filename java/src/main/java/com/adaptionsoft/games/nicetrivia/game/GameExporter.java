package com.adaptionsoft.games.nicetrivia.game;

import com.adaptionsoft.games.nicetrivia.player.Player;
import com.adaptionsoft.games.nicetrivia.player.PlayerExporter;

public class GameExporter{

    public static GameInformation export(Game game) {
        final GameInformation gameInformation = new GameInformation();
        for (Player player:game.players) {
            gameInformation.players.add(PlayerExporter.export(player));
        }
        return gameInformation;
    }
}
